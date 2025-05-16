package com.basic.aop;

import com.basic.annotation.RedisLock;
import com.basic.enums.RedissonLockType;
import com.basic.exception.RedisLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

/**
 * 分布式锁切面
 *
 * @author vains
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RedisLockAspect {

    /**
     * 默认分布式锁key的分隔符
     */
    private static final String DELIMITER = ":";

    private final RedissonClient redissonClient;

    /**
     * 默认分布式锁key的前缀
     */
    private static final String KEY_PREFIX = "lock:";

    /**
     * SpEL表达式解析
     */
    private final SpelExpressionParser expressionParser = new SpelExpressionParser();

    /**
     * 用于获取方法参数名字
     */
    private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    @Pointcut("@annotation(com.basic.annotation.RedisLock)")
    public void redisLock() {
    }

    @Around("redisLock()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取分布式锁注解RedisLock
        RedisLock redisLock = this.getRedisLockAnno(pjp);
        if (redisLock == null) {
            log.warn("获取注解失败，请检查aop配置.");
            return pjp.proceed();
        }

        String defaultKey;
        // 检验是否需要生成默认的key
        if (ObjectUtils.isEmpty(redisLock.value())) {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();
            // 根据方法生成默认的key
            defaultKey = String.join(DELIMITER, pjp.getTarget().getClass().getName(), method.getName());
            if (log.isDebugEnabled()) {
                log.debug("Redis分布式锁的key未指定，默认根据方法生成key：[{}].", defaultKey);
            }
        } else {
            defaultKey = null;
        }

        String lockKey;
        if (ObjectUtils.isEmpty(defaultKey)) {
            lockKey = this.getLockKey(pjp, redisLock);
        } else {
            lockKey = KEY_PREFIX.concat(defaultKey);
        }

        // 根据指定锁类型获取锁
        RedissonLockType lockType;
        if (redisLock.lockType() == null) {
            lockType = RedissonLockType.R_LOCK;
        } else {
            lockType = redisLock.lockType();
        }

        // 获取锁
        RLock lock = lockType.getLock(redissonClient, lockKey);

        try {
            // 是否成功获取锁
            boolean success;
            if (redisLock.leaseTime() != -1) {
                success = lock.tryLock(redisLock.waitTime(), redisLock.leaseTime(), redisLock.timeUnit());
            } else {
                success = lock.tryLock(redisLock.waitTime(), redisLock.timeUnit());
            }

            if (log.isDebugEnabled()) {
                log.debug("Redis锁的key是[{}]，尝试获取锁的状态为[{}]", lockKey, success);
            }

            if (!success) {
                throw new RedisLockException(redisLock.message());
            }

            // 执行上锁方法
            return pjp.proceed();
        } catch (InterruptedException e) {
            log.error("Redis分布式锁加锁时中断", e);
            throw new RedisLockException(e.getMessage());
        } finally {
            if (lock.isHeldByCurrentThread()) {
                if (log.isDebugEnabled()) {
                    log.debug("Redis分布式锁[{}]被当前线程持有，释放锁.", lockKey);
                }
                lock.unlock();
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Redis分布式锁[{}]未被当前线程持有，忽略.", lockKey);
                }
            }
        }
    }

    /**
     * 获取切面方法的分布式锁注解{@link RedisLock}
     *
     * @param pjp 切面方法执行者
     * @return 分布式锁注解
     * @throws NoSuchMethodException 方法找不到时抛出
     */
    private RedisLock getRedisLockAnno(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        String methodName = pjp.getSignature().getName();
        Class<?> clazz = pjp.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method lockMethod = clazz.getMethod(methodName, par);
        return lockMethod.getAnnotation(RedisLock.class);
    }

    /**
     * 获取 lockKey
     *
     * @param pjp       方法执行者
     * @param redisLock 分布式锁注解
     * @return redis锁的key
     */
    private String getLockKey(ProceedingJoinPoint pjp, RedisLock redisLock) {
        String lockKey = redisLock.value();
        Assert.hasText(lockKey, "lockKey must not be empty");
        if (lockKey.contains("#")) {
            this.checkSpEL(lockKey);
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            // 获取方法参数值
            Object[] args = pjp.getArgs();
            lockKey = getValBySpEL(lockKey, methodSignature, args);
        }
        return KEY_PREFIX.concat(lockKey);
    }

    /**
     * 解析spEL表达式
     *
     * @param spEL            spEL表达式
     * @param methodSignature 方法
     * @param args            参数列表
     * @return 表达式的值
     */
    private String getValBySpEL(String spEL, MethodSignature methodSignature, Object[] args) {
        // 获取方法形参名数组
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        if (paramNames == null || paramNames.length < 1) {
            throw new RuntimeException("Lok key cannot be empty");
        }
        Expression expression = expressionParser.parseExpression(spEL);
        // spring的表达式上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        // 给上下文赋值
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        Object value = expression.getValue(context);
        if (value == null) {
            throw new RedisLockException("The parameter value (spEL value) cannot be null");
        }
        return value.toString();
    }

    /**
     * SpEL 表达式校验
     *
     * @param spEL Spring表达式
     */
    private void checkSpEL(String spEL) {
        try {
            ExpressionParser parser = new SpelExpressionParser();
            parser.parseExpression(spEL, new TemplateParserContext());
        } catch (Exception e) {
            log.error("spEL表达式解析异常", e);
            throw new RedisLockException("Invalid SpEL expression [" + spEL + "]");
        }
    }

}

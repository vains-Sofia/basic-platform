package com.basic.configuration;

import com.basic.util.RedisConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * Redis 配置
 *
 * @author vains
 */
@Configuration(proxyBeanMethods = false)
public class RedisConfiguration {

    /**
     * 默认情况下使用
     *
     * @param connectionFactory Redis 链接工厂
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
        JsonMapper.Builder jsonMapperBuilder = JsonMapper.builderWithJackson2Defaults();
        // 日期不序列化为 Timestamps
        jsonMapperBuilder.disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapperBuilder.disable(DateTimeFeature.WRITE_DURATIONS_AS_TIMESTAMPS);

        JsonMapper.Builder builder = RedisConfigUtils.buildRedisObjectMapper(jsonMapperBuilder);

        // 存入 Redis时序列化值的序列化器
        JacksonJsonRedisSerializer<Object> valueSerializer =
                new JacksonJsonRedisSerializer<>(builder.build(), Object.class);

        return RedisConfigUtils.buildRedisTemplate(connectionFactory, valueSerializer);
    }

}

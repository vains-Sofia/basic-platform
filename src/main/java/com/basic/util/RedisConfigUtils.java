package com.basic.util;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.jackson.CoreJacksonModule;
import org.springframework.security.jackson.SecurityJacksonModules;
import tools.jackson.core.StreamReadFeature;
import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * Redis 配置类工具
 *
 * @author vains
 */
@UtilityClass
public class RedisConfigUtils {

    public static JsonMapper.Builder buildRedisObjectMapper(JsonMapper.Builder builder) {
        builder.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 序列化所有字段
//        builder.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 此项必须配置，否则如果序列化的对象里边还有对象，会报如下错误：
        //     java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to XXX
        builder.activateDefaultTyping(
                builder.baseSettings().getPolymorphicTypeValidator(),
                DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY);

        builder.enable(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION);

        // 加载 Security提供的Module
        ClassLoader classLoader = RedisConfigUtils.class.getClassLoader();
        builder.addModules(SecurityJacksonModules.getModules(classLoader));
        builder.addModule(new CoreJacksonModule());

        return builder;
    }

    public static RedisTemplate<Object, Object> buildRedisTemplate(RedisConnectionFactory connectionFactory,
                                                                   GenericJacksonJsonRedisSerializer valueSerializer) {
        // 字符串序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        // 设置值序列化
        redisTemplate.setValueSerializer(valueSerializer);
        // 设置 Hash格式数据值的序列化器
        redisTemplate.setHashValueSerializer(valueSerializer);
        // 默认的Key序列化器为：JdkSerializationRedisSerializer
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 设置字符串序列化器
        redisTemplate.setStringSerializer(stringRedisSerializer);
        // 设置 Hash结构的key的序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // 设置连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

}

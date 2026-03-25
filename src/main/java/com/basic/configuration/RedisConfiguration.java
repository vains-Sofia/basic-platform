package com.basic.configuration;

import com.basic.util.RedisConfigUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import tools.jackson.databind.json.JsonMapper;

/**
 * Redis 配置
 *
 * @author vains
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class RedisConfiguration {

    private final JsonMapper.Builder jsonMapperBuilder;

    /**
     * 默认情况下使用
     *
     * @param connectionFactory Redis 链接工厂
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {

        JsonMapper.Builder builder = RedisConfigUtils.buildRedisObjectMapper(jsonMapperBuilder);

        // 存入 Redis时序列化值的序列化器
        GenericJacksonJsonRedisSerializer valueSerializer = new GenericJacksonJsonRedisSerializer(builder.build());

        return RedisConfigUtils.buildRedisTemplate(connectionFactory, valueSerializer);
    }

}

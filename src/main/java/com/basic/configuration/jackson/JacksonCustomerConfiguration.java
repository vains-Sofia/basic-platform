package com.basic.configuration.jackson;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.cfg.DateTimeFeature;

/**
 * Jackson 自定义配置
 *
 * @author vains
 */
@Configuration(proxyBeanMethods = false)
@Import({WebMvcDateTimeConfiguration.class})
public class JacksonCustomerConfiguration {

    @Bean
    public JsonMapperBuilderCustomizer jsonMapperBuilderCustomizer() {
        return builder -> {
            builder.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

            // 自定义 Java Time 日期序列化格式
            builder.addModule(new JavaTimeJacksonModule());

            // 日期不序列化为 Timestamps
            builder.disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS);
            builder.disable(DateTimeFeature.WRITE_DURATIONS_AS_TIMESTAMPS);

            builder.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        };
    }

}

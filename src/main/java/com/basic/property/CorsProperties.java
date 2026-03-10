package com.basic.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  跨域配置
 *
 * @author vains
 */
@Data
@Component
@ConfigurationProperties(prefix = "basic.cloud.cors")
public class CorsProperties {

    /**
     * 允许跨域的“源”，默认 <a href="http://127.0.0.1:5173">http://127.0.0.1:5173</a>
     */
    private List<String> allowedOrigins = List.of("http://127.0.0.1:5173");

}
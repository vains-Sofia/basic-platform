package com.basic.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 登录配置
 *
 * @author vains
 */
@Data
@Component
@ConfigurationProperties(prefix = "basic.cloud.security.login")
public class LoginProperties {

    /**
     * 前端项目地址
     */
    private String frontEndUrl;

}
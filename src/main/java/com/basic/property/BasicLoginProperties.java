package com.basic.property;

import com.basic.constant.AuthorizeConstants;
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
public class BasicLoginProperties {

    /**
     * 前端项目地址
     */
    private String frontEndUrl;

    /**
     * 邮件验证码配置
     */
    private BasicLoginCaptchaRequestProperties email = new BasicLoginCaptchaRequestProperties(AuthorizeConstants.EMAIL_PARAMETER);


}
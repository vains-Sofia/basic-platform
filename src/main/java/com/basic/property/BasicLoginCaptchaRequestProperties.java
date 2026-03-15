package com.basic.property;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * 验证码配置
 *
 * @author vains
 */
@Data
@NoArgsConstructor
public class BasicLoginCaptchaRequestProperties {

    /**
     * 初始化 验证码的key 在请求中的参数名
     *
     * @param captchaKeyParameter 验证码的key 在请求中的参数名
     */
    public BasicLoginCaptchaRequestProperties(String captchaKeyParameter) {
        this.captchaKeyParameter = captchaKeyParameter;
    }

    /**
     * 验证码有效时长
     */
    private Long captchaEffective = 300L;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    /**
     * 获取 验证码的id 的参数名
     */
    private String captchaKeyParameter;

    /**
     * 验证码参数名
     */
    private String captchaParameter = "captcha";

}

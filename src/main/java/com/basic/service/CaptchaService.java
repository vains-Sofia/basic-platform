package com.basic.service;

/**
 * 验证码 Repository 存储接口
 *
 * @author vains
 */
public interface CaptchaService {

    /**
     * 根据当前请求保存验证码
     *
     * @param captcha 验证码
     */
    void save(String captcha);

    /**
     * 根据当前请求去校验验证码
     */
    void validate();

}
package com.basic.service;

import com.basic.domain.response.TokenResponse;
import org.springframework.security.core.Authentication;

/**
 * token service接口
 *
 * @author vains
 */
public interface TokenService {

    /**
     * 创建access_token
     *
     * @param authentication 当前认证信息
     * @param expiry         过期时间
     * @return access_token
     */
    String generateAccessToken(Authentication authentication, long expiry);

    /**
     * 构建token响应bean
     *
     * @param authentication     当前认证信息
     * @param accessTokenExpiry  access_token过期时间
     * @param refreshTokenExpiry refresh_token过期时间
     * @return token响应bean
     */
    TokenResponse generateTokenResponse(Authentication authentication, long accessTokenExpiry, long refreshTokenExpiry);

}

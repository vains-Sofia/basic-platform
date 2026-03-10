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
     * 构建token响应bean
     *
     * @param authentication     当前认证信息
     * @return token响应bean
     */
    TokenResponse generateToken(Authentication authentication);

    TokenResponse refreshToken(String refreshToken);

    void revokeToken(String accessToken);

}

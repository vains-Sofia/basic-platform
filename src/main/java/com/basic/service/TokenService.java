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
     * 构建token 响应bean
     *
     * @param authentication 当前认证信息
     * @return token 响应bean
     */
    TokenResponse generateToken(Authentication authentication);

    /**
     * 通过 Refresh Token刷新token
     *
     * @param refreshToken Refresh Token
     * @return 新的 token
     */
    TokenResponse refreshToken(String refreshToken);

    /**
     * 退出登录时 撤销token
     */
    void revokeToken();

}

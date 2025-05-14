package com.basic.handler.security;

import com.basic.constant.AuthorizeConstants;
import com.basic.domain.Result;
import com.basic.domain.response.TokenResponse;
import com.basic.service.TokenService;
import com.basic.util.ServletUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;

import java.io.IOException;

/**
 * 登录成功处理类
 *
 * @author vains
 */
@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final String loginPageUri;

    private final TokenService tokenService;

    private final AuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("登录成功，用户: {}.", authentication.getName());
        // 如果是绝对路径(前后端分离)
        if (UrlUtils.isAbsoluteUrl(this.loginPageUri)) {
            log.debug("登录页面为独立的前端服务页面，写回json.");

            // 生成JWT Token并返回
            TokenResponse tokenResponse = tokenService.generateTokenResponse(
                    authentication, AuthorizeConstants.ACCESS_TOKEN_EXPIRY, AuthorizeConstants.REFRESH_TOKEN_EXPIRY);

            // 写回数据
            Result<TokenResponse> success = Result.success(tokenResponse);
            ServletUtils.renderJson(response, success);
        } else {
            log.debug("登录页面为认证服务的相对路径，跳转至之前的页面.");
            authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        }
    }

}

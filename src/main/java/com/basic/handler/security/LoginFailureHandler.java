package com.basic.handler.security;

import com.basic.domain.Result;
import com.basic.util.ServletUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;

import java.io.IOException;

/**
 * 登录失败处理类
 *
 * @author vains
 */
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final String loginPageUri;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    public LoginFailureHandler(String loginPageUri) {
        this.loginPageUri = loginPageUri;
        String loginFailureUrl = this.loginPageUri + "?error";
        this.authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler(loginFailureUrl);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.debug("登录失败，原因：{}", exception.getMessage());
        // 如果是绝对路径(前后端分离)
        if (UrlUtils.isAbsoluteUrl(this.loginPageUri)) {
            log.debug("登录页面为独立的前端服务页面，写回json.");
            // 登录失败，写回401与具体的异常
            Result<String> success = Result.error(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
            ServletUtils.renderJson(response, success);
        } else {
            log.debug("登录页面为认证服务的相对路径，跳转至：{}", this.loginPageUri);
            authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
        }
    }

}

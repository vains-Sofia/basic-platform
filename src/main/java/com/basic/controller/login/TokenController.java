package com.basic.controller.login;

import com.basic.domain.Result;
import com.basic.domain.request.RefreshTokenRequest;
import com.basic.domain.response.TokenResponse;
import com.basic.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * token接口
 *
 * @author vains
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
@Tag(name = "登录-Token相关接口", description = "Token 相关接口")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    @Operation(summary = "刷新 Access Token 接口", description = "根据Refresh Token刷新 Access Token")
    public TokenResponse refresh(@RequestBody @Valid RefreshTokenRequest request) {

        return tokenService.refreshToken(request.getRefreshToken());
    }

    @PostMapping("/logout")
    @Operation(summary = "撤销 Access Token 接口", description = "针对当前请求携带的 Access Token 撤销")
    public Result<String> logout() {
        tokenService.revokeToken();
        return Result.success("退出登录成功.");
    }


}

package com.basic.controller.login;

import com.basic.domain.Result;
import com.basic.domain.request.RefreshTokenRequest;
import com.basic.domain.response.TokenResponse;
import com.basic.service.TokenService;
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
@Tag(name = "Token 相关接口", description = "Token 相关接口")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody @Valid RefreshTokenRequest request) {

        return tokenService.refreshToken(request.getRefreshToken());
    }

    @PostMapping("/logout")
    public Result<String> logout() {
        tokenService.revokeToken();
        return Result.success("退出登录成功.");
    }


}

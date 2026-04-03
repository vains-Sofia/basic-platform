package com.basic.controller.login;

import com.basic.domain.Result;
import com.basic.domain.response.TokenResponse;
import com.basic.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public TokenResponse refresh(@RequestParam String refreshToken) {

        return tokenService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    public Result<String> logout() {
        tokenService.revokeToken();
        return Result.success("退出登录成功.");
    }


}

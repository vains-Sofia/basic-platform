package com.basic.controller;

import com.basic.domain.response.TokenResponse;
import com.basic.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public TokenResponse refresh(@RequestParam String refreshToken) {

        return tokenService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String auth) {

        String token = auth.replace("Bearer ", "");

        tokenService.revokeToken(token);
    }


}

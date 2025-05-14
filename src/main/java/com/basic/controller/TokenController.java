package com.basic.controller;

import com.basic.constant.AuthorizeConstants;
import com.basic.domain.Result;
import com.basic.domain.request.RefreshTokenRequest;
import com.basic.domain.response.TokenResponse;
import com.basic.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * token接口
 *
 * @author vains
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
@Tag(name = "Token相关接口", description = "Token相关接口")
public class TokenController {

    private final TokenService tokenService;

    @Resource
    private RedisTemplate<String, Authentication> redisTemplate;

    @PostMapping("/refreshToken")
    @Operation(summary = "刷新token", description = "刷新token")
    public Result<TokenResponse> refreshToken(@Validated @RequestBody RefreshTokenRequest request) {
        Authentication authentication = redisTemplate.opsForValue()
                .get(AuthorizeConstants.AUTHENTICATION_PREFIX + request.getRefreshToken());
        TokenResponse tokenResponse = tokenService.generateTokenResponse(
                authentication, AuthorizeConstants.ACCESS_TOKEN_EXPIRY, AuthorizeConstants.REFRESH_TOKEN_EXPIRY);
        return Result.success(tokenResponse);
    }

}

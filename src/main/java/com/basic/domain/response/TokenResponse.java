package com.basic.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "TokenResponse", description = "登录生成的 Token 响应")
public class TokenResponse {

    @JsonProperty("access_token")
    @Schema(description = "access token，请求时使用")
    private String accessToken;

    @JsonProperty("refresh_token")
    @Schema(description = "Refresh token，access token过期时使用该 token 刷新")
    private String refreshToken;

    @JsonProperty("token_type")
    @Schema(description = "access token类型，默认Bearer")
    private String tokenType = "Bearer";

    @JsonProperty("expires_in")
    @Schema(description = "access token的过期时间")
    private long expiresIn;

}
package com.basic.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录请求入参
 *
 * @author vains
 */
@Data
@Schema(title = "刷新access_token入参", description = "刷新access_token入参")
public class RefreshTokenRequest {

    /**
     * 刷新token，登录时获取的refresh_token
     */
    @NotBlank
    @Schema(title = "刷新token", description = "登录时获取的refresh_token")
    private String refreshToken;

}

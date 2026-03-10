package com.basic.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Schema(title = "RefreshTokenInfo", description = "刷新 token信息")
public class RefreshTokenInfo implements Serializable {

    @Schema(description = "用户 ID")
    private String userId;

    @Schema(description = "用户姓名")
    private String username;

    @Schema(description = "用户权限")
    private String scope;

    @Schema(description = "Refresh token 过期时间")
    private Instant expireAt;

}
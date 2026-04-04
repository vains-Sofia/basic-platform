package com.basic.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "RefreshTokenInfo", description = "刷新 token信息")
public class RefreshTokenInfo implements Serializable {

    @Schema(description = "用户信息")
    private BasicUserDetails userDetails;

    @Schema(description = "绝对过期时间戳（毫秒）")
    private Long absoluteExpireTime;

}
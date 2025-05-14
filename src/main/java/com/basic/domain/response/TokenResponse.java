package com.basic.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录成功响应
 *
 * @author vains
 */
@Data
public class TokenResponse implements Serializable {

    /**
     * 用户id
     */
    @Schema(title = "用户id", description = "用户id")
    private String id;

    /**
     * 头像
     */
    @Schema(title = "头像", description = "头像")
    private String avatar;

    /**
     * 用户名
     */
    @Schema(title = "用户名", description = "用户名")
    private String username;

    /**
     * 昵称
     */
    @Schema(title = "昵称", description = "昵称")
    private String nickname;

    /**
     * 当前登录用户的角色
     */
    @Schema(title = "角色", description = "当前登录用户的角色")
    private Set<String> roles;

    /**
     * `token`
     */
    @Schema(title = "token", description = "token")
    private String accessToken;

    /**
     * 用于调用刷新`accessToken`的接口时所需的`token`
     */
    @Schema(title = "刷新token", description = "用于调用刷新`accessToken`的接口时所需的`token`")
    private String refreshToken;

    /**
     * `accessToken`的过期时间（格式'xxxx/xx/xx xx:xx:xx'）
     */
    @Schema(title = "过期时间", description = "`accessToken`的过期时间，时间戳")
    private long expires;

}

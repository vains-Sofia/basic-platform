package com.basic.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

/**
 * 认证后用户信息响应
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthenticatedUserResponse extends FindBasicUserResponse {

    /**
     * 角色列表
     */
    @Schema(description = "角色列表")
    private List<String> roles;

    /**
     * 权限列表
     */
    @Schema(description = "权限列表")
    private Set<GrantedAuthority> authorities;

}

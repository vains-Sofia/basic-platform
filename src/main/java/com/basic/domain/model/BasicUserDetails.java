package com.basic.domain.model;

import com.basic.domain.entity.SysBasicUser;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Security 用户信息实现
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicUserDetails extends SysBasicUser implements UserDetails {

    /**
     * 权限信息
     */
    private Collection<? extends GrantedAuthority> authorities;

    @Nonnull
    @Override
    public String getUsername() {
        return super.getUsername();
    }
}

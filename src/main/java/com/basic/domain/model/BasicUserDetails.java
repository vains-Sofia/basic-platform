package com.basic.domain.model;

import com.basic.domain.entity.SysBasicUser;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.util.Collection;

/**
 * Security 用户信息实现
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(as = BasicUserDetails.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
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

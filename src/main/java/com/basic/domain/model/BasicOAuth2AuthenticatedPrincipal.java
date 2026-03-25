package com.basic.domain.model;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础OAuth2资源服务用户信息(等同基础用户信息)
 *
 * @author vains
 * @see BasicUserDetails
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicOAuth2AuthenticatedPrincipal extends BasicUserDetails implements OAuth2AuthenticatedPrincipal {

    private Map<String, Object> attributes = new HashMap<>();

    @Nonnull
    @Override
    public String getName() {
        return super.getNickname();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
}

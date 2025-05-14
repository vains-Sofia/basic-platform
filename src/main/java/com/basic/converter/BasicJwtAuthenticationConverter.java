package com.basic.converter;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

/**
 * jwt token转换器
 *
 * @author vains
 */
@Component
public class BasicJwtAuthenticationConverter extends JwtAuthenticationConverter {

    public BasicJwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // 设置解析权限信息的前缀，设置为空是去掉前缀
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        super.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    }
}

package com.basic.config;

import com.basic.config.doc.ApiEnumParameterCustomizer;
import com.basic.config.doc.ApiEnumPropertyCustomizer;
import com.basic.property.DocProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 在线文档自动配置类
 *
 * @author vains
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "springdoc.api-docs.enabled", matchIfMissing = true)
@Import({
        ApiEnumPropertyCustomizer.class,
        ApiEnumParameterCustomizer.class,
})
public class OpenApiConfiguration {

    private final DocProperties properties;

    private final SwaggerUiConfigProperties swaggerUiConfigProperties;

    private final SpringDocConfigProperties springDocConfigProperties;

    @Bean
    public OpenAPI openApi() {
        // 接口在没有RequestBody注解时默认展开对象参数
        springDocConfigProperties.setDefaultFlatParamObject(Boolean.TRUE);
        springDocConfigProperties.setShowLoginEndpoint(Boolean.TRUE);
        // 默认收起所有标签
        String docExpansion = swaggerUiConfigProperties.getDocExpansion();
        if (ObjectUtils.isEmpty(docExpansion)) {
            swaggerUiConfigProperties.setDocExpansion("none");
        }
        // 许可证信息
        License license = new License()
                .name(properties.getLicense().getName())
                .url(properties.getLicense().getUrl());
        // 基础信息
        Info info = new Info()
                .title(properties.getInfo().getTitle())
                .version(properties.getInfo().getVersion())
                .description(properties.getInfo().getDescription())
                .termsOfService(properties.getInfo().getTermsOfService())
                .license(license);

        // 组件
        Components components = new Components();

        // yaml中的认证相关配置
        List<DocProperties.CloudDocSecurity> cloudDocSecurities = properties.getSecurity();

        // 安全认证组件
        SecurityRequirement securityRequirement = new SecurityRequirement();

        if (!ObjectUtils.isEmpty(cloudDocSecurities)) {
            for (DocProperties.CloudDocSecurity cloudDocSecurity : cloudDocSecurities) {
                SecurityScheme securityScheme = new SecurityScheme();
                // 创建一个oauth认证流程
                OAuthFlows oAuthFlows = new OAuthFlows();
                // 项目yaml中的scope配置
                List<DocProperties.CloudDocScope> cloudDocScopes = cloudDocSecurity.getScopes();
                // 文档scope配置类
                Scopes scopes = new Scopes();
                if (!ObjectUtils.isEmpty(cloudDocScopes)) {
                    // 添加认证scope配置
                    cloudDocScopes.forEach(cloudDocScope -> scopes.addString(cloudDocScope.getName(), cloudDocScope.getDescription()));
                }
                // 设置OAuth2流程中认证服务的基本信息
                OAuthFlow oAuthFlow = new OAuthFlow()
                        // 授权申请地址
                        .authorizationUrl(cloudDocSecurity.getAuthorizeUrl())
                        // 获取token地址
                        .tokenUrl(cloudDocSecurity.getAccessTokenUrl())
                        .scopes(scopes);

                // 根据配置类型选择使用的授权模式
                switch (cloudDocSecurity.getGrantType()) {
                    case PASSWORD -> oAuthFlows.password(oAuthFlow);
                    case AUTHORIZATION_CODE -> oAuthFlows.authorizationCode(oAuthFlow);
                    case CLIENT_CREDENTIALS -> oAuthFlows.clientCredentials(oAuthFlow);
                }

                // OAuth2流程
                securityScheme.flows(oAuthFlows)
                        .type(SecurityScheme.Type.OAUTH2);

                // 将认证配置加入组件中
                components.addSecuritySchemes(cloudDocSecurity.getName(), securityScheme);
                // 将安全认证和swagger-ui关联起来
                securityRequirement.addList(cloudDocSecurity.getName());
            }
        } else {
            SecurityScheme securityScheme = new SecurityScheme();
            // OAuth2流程
            securityScheme.type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .in(SecurityScheme.In.HEADER)
                    .name(HttpHeaders.AUTHORIZATION);

            // 将认证配置加入组件中
            components.addSecuritySchemes("access_token", securityScheme);
            // 将安全认证和swagger-ui关联起来
            securityRequirement.addList("access_token");
        }

        // 组装servers
        List<Server> servers = null;
        List<String> serversList = properties.getInfo().getServers();
        if (!ObjectUtils.isEmpty(serversList)) {
            servers = serversList.stream()
                    .map(server -> new Server().url(server))
                    .toList();
        }

        return new OpenAPI()
                // 基础描述信息
                .info(info)
                // 设置服务访问地址
                .servers(servers)
                // 添加OAuth2认证流程组件
                .components(components)
                // 添加请求时携带OAuth2规范的请求头(通过OAuth2流程获取token后发请求时会自动携带Authorization请求头)
                .addSecurityItem(securityRequirement);
    }

}

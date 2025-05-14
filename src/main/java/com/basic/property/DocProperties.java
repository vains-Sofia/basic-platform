package com.basic.property;

import com.basic.enums.BasicEnum;
import io.swagger.v3.oas.models.info.Contact;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 在线文档yaml配置
 *
 * @author vains
 */
@Data
@Component
@ConfigurationProperties(prefix = DocProperties.PREFIX)
public class DocProperties {

    /**
     * 配置在yaml中的前缀
     */
    static final String PREFIX = "basic.cloud.doc";

    /**
     * 在线文档基本配置信息
     */
    private CloudDocInfo info = new CloudDocInfo();

    /**
     * 许可证信息
     */
    private CloudDocLicense license = new CloudDocLicense();

    /**
     * 认证信息
     */
    private List<CloudDocSecurity> security = new ArrayList<>();

    /**
     * 在线文档基本信息
     *
     * @author vains
     */
    @Data
    public static class CloudDocInfo {

        /**
         * 文档标题
         */
        private String title = "在线文档";

        /**
         * 描述信息
         */
        private String description;

        /**
         * 服务条件
         */
        private String termsOfService;

        /**
         * 版本说明
         */
        private String version;

        /**
         * 网关地址
         */
        private List<String> servers;

        /**
         * 联系人信息
         */
        private Contact contact = new Contact();

    }

    /**
     * 在线文档许可证
     *
     * @author vains
     */
    @Data
    public static class CloudDocLicense {

        /**
         * 许可证名
         */
        private String name;

        /**
         * 许可证地址
         */
        private String url;

    }

    /**
     * 在线文档认证
     *
     * @author vains
     */
    @Data
    public static class CloudDocSecurity {

        /**
         * 认证配置名称
         */
        private String name;

        /**
         * 获取access token地址
         */
        private String accessTokenUrl;

        /**
         * 授权申请地址
         */
        private String authorizeUrl;

        /**
         * 认证方式
         */
        private CloudDocGrantTypeEnum grantType;

        /**
         * 授权申请的scope
         */
        private List<CloudDocScope> scopes;

    }

    /**
     * 在线文档认证scope
     *
     * @author vains
     */
    @Data
    public static class CloudDocScope {

        /**
         * scope值
         */
        private String name;

        /**
         * scope描述
         */
        private String description;

    }

    /**
     * 在线文档认证方式
     *
     * @author vains
     */
    @RequiredArgsConstructor
    public enum CloudDocGrantTypeEnum implements BasicEnum<String, CloudDocGrantTypeEnum> {

        /**
         * 授权码模式
         */
        AUTHORIZATION_CODE("authorization_code"),

        /**
         * 密码模式
         */
        PASSWORD("password"),

        /**
         * 客户端认证模式
         */
        CLIENT_CREDENTIALS("client_credentials");

        /**
         * 认证类型
         */
        private final String grantType;

        @Override
        public String getValue() {
            return this.grantType;
        }
    }

}

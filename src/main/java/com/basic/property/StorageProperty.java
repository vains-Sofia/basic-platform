package com.basic.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件存储服务配置
 *
 * @author vains
 */
@Data
@Component
@ConfigurationProperties(prefix = StorageProperty.PREFIX)
public class StorageProperty {

    /**
     * 配置在 yaml 中的前缀
     */
    static final String PREFIX = "basic.cloud.minio";

    /**
     * 文件存储服务的访问地址
     */
    private String endpoint;

    /**
     * 文件存储服务认证的 访问key
     */
    private String accessKey;

    /**
     * 文件存储服务认证的密钥
     */
    private String secretKey;

    /**
     * 文件存储服务的存储桶名称
     */
    private String bucket;

    /**
     * 如果有代理服务器，则需要配置代理服务器的地址，这样签名后才能使用代理服务器的地址访问
     */
    private String proxyEndpoint;
}

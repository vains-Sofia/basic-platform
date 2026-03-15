package com.basic.configuration.minio;

import com.basic.property.StorageProperty;
import io.minio.MinioAsyncClient;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO客户端配置
 *
 * @author vains
 */
@Slf4j
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class MinioClientConfiguration {

    private final StorageProperty storageProperty;

    /**
     * 将MinIO Client注册为Bean
     *
     * @return MinioClient实例
     */
    @Bean
    public MinioClient minioClient() {
        try {

            MinioClient minioClient = MinioClient.builder()
                    .endpoint(storageProperty.getEndpoint())
                    .credentials(storageProperty.getAccessKey(), storageProperty.getSecretKey())
                    .build();
            return new MutableMinioClient(minioClient, createMinioAsyncClient());
        } catch (Exception e) {
            log.error("初始化minio配置异常: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 创建支持代理的MinioAsyncClient
     *
     * @return MutableMinioAsyncClient
     */
    private MutableMinioAsyncClient createMinioAsyncClient() {
        MinioAsyncClient.Builder asyncClientBuilder = MinioAsyncClient.builder();
        asyncClientBuilder.endpoint(storageProperty.getEndpoint());
        asyncClientBuilder.credentials(storageProperty.getAccessKey(), storageProperty.getSecretKey());
        MinioAsyncClient minioAsyncClient = asyncClientBuilder.build();
        return new MutableMinioAsyncClient(minioAsyncClient, storageProperty.getProxyEndpoint());
    }

}
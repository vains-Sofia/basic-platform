package com.basic.configuration;

import com.basic.property.StorageProperty;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO客户端 配置
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
     * @return MinioClient 实例
     */
    @Bean
    public MinioClient minioClient() {
        try {

            return MinioClient.builder()
                    .endpoint(storageProperty.getEndpoint())
                    .credentials(storageProperty.getAccessKey(), storageProperty.getSecretKey())
                    .build();
        } catch (Exception e) {
            log.error("初始化 MinIO 配置异常: {}", e.getMessage());
        }
        return null;
    }

}
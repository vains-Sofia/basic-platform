package com.basic.configuration.minio;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.Http;
import io.minio.MinioAsyncClient;
import io.minio.credentials.Credentials;
import io.minio.errors.MinioException;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.CompletionException;
import java.util.function.Supplier;

/**
 * 可变端点的 Minio异步客户端
 *
 * @author vains
 */
public class MutableMinioAsyncClient extends MinioAsyncClient {

    // 代理端点
    private final Supplier<String> proxyEndpointSupplier;

    protected MutableMinioAsyncClient(MinioAsyncClient client, Supplier<String> proxyEndpointSupplier) {
        super(client);
        this.proxyEndpointSupplier = proxyEndpointSupplier;
    }

    @Override
    public String getPresignedObjectUrl(GetPresignedObjectUrlArgs args)
            throws MinioException {
        checkArgs(args);
        String region = null;
        try {
            region = this.getRegion(args.bucket(), args.region()).join();
        } catch (CompletionException e) {
            this.throwMinioException(e);
        }

        Http.QueryParameters queryParams = new Http.QueryParameters();
        if (args.versionId() == null) {
            queryParams.put("versionId", args.versionId());
        }

        Credentials credentials = this.provider == null ? null : this.provider.fetch();
        if (credentials != null && credentials.sessionToken() != null) {
            queryParams.put("X-Amz-Security-Token", credentials.sessionToken());
        }

        Http.BaseUrl proxyBaseUrl;
        // 重点：修改url
        String proxyEndpoint = proxyEndpointSupplier == null ? null : proxyEndpointSupplier.get();
        if (StringUtils.isNotBlank(proxyEndpoint)) {
            if (proxyEndpoint.endsWith("/")) {
                proxyBaseUrl = new Http.BaseUrl(proxyEndpoint);
            } else {
                proxyBaseUrl = new Http.BaseUrl(proxyEndpoint + "/");
            }
        } else {
            proxyBaseUrl = baseUrl;
        }

        return Http.S3Request.builder()
                .userAgent(this.userAgent)
                .method(args.method())
                .args(args)
                .queryParams(queryParams)
                .build()
                .toPresignedRequest(proxyBaseUrl, region, credentials, args.expiry())
                .httpRequest()
                .url()
                .toString();
    }
}

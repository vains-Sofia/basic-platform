package com.basic.configuration.minio;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;

/**
 * 可变端点的 Minio客户端
 *
 * @author vains
 */
public class MutableMinioClient extends MinioClient {

    private final MutableMinioAsyncClient asyncClient;

    public MutableMinioClient(MinioClient client, MutableMinioAsyncClient asyncClient) {
        super(client);
        this.asyncClient = asyncClient;
    }

    @Override
    public String getPresignedObjectUrl(GetPresignedObjectUrlArgs args) throws MinioException {
        return asyncClient.getPresignedObjectUrl(args);
    }
}
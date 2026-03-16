package com.basic.configuration.minio;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
    public String getPresignedObjectUrl(GetPresignedObjectUrlArgs args)
            throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return asyncClient.getPresignedObjectUrl(args);
    }
}
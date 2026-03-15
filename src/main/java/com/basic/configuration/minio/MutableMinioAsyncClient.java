package com.basic.configuration.minio;

import com.google.common.collect.Multimap;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioAsyncClient;
import io.minio.Signer;
import io.minio.credentials.Credentials;
import io.minio.errors.*;
import io.minio.http.HttpUtils;
import io.minio.http.Method;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

/**
 * 可变端点的 Minio异步客户端
 *
 * @author vains
 */
public class MutableMinioAsyncClient extends MinioAsyncClient {

    // 代理端点
    private final String proxyEndpoint;

    protected MutableMinioAsyncClient(MinioAsyncClient client, String proxyEndpoint) {
        super(client);
        this.proxyEndpoint = proxyEndpoint;
    }

    @Override
    public String getPresignedObjectUrl(GetPresignedObjectUrlArgs args)
            throws ErrorResponseException, InsufficientDataException, InternalException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            XmlParserException, ServerException {
        checkArgs(args);

        byte[] body =
                (args.method() == Method.PUT || args.method() == Method.POST) ? HttpUtils.EMPTY_BODY : null;

        Multimap<String, String> queryParams = newMultimap(args.extraQueryParams());
        if (args.versionId() != null) queryParams.put("versionId", args.versionId());

        String region = null;
        try {
            region = getRegionAsync(args.bucket(), args.region()).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throwEncapsulatedException(e);
        }

        if (provider == null) {
            HttpUrl url = buildUrl(args.method(), args.bucket(), args.object(), region, queryParams);
            return url.toString();
        }

        Credentials credentials = provider.fetch();
        if (credentials.sessionToken() != null) queryParams.put("X-Amz-Security-Token", credentials.sessionToken());
        HttpUrl url = buildUrl(args.method(), args.bucket(), args.object(), region, queryParams);
        // 重点：修改url
        if (StringUtils.isNotBlank(proxyEndpoint)) {
            HttpUrl.Builder builder = url.newBuilder(url.toString().replace(baseUrl.toString(), proxyEndpoint));
            if (builder != null) {
                url = builder.build();
            }
        }
        Request request =
                createRequest(
                        url,
                        args.method(),
                        args.extraHeaders() == null ? null : httpHeaders(args.extraHeaders()),
                        body,
                        0,
                        credentials);
        url = Signer.presignV4(request, region, credentials.accessKey(), credentials.secretKey(), args.expiry());
        return url.toString();
    }
}
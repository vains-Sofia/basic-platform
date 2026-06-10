package com.basic.service.impl;

import com.basic.domain.request.FilePreSignedRequest;
import com.basic.domain.response.FilePreSignedResponse;
import com.basic.exception.CloudServiceException;
import com.basic.property.StorageProperty;
import com.basic.service.FileService;
import com.basic.util.ServletUtils;
import io.minio.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 文件相关操作 MinIO 实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MinioFileServiceImpl implements FileService {

    private final MinioClient minioClient;

    private final StorageProperty storageProperty;

    @Override
    public FilePreSignedResponse filePreSigned(FilePreSignedRequest request) {
        try {
            HttpServletRequest httpRequest = ServletUtils.getRequest();
            if (httpRequest == null) {
                log.error("获取 HttpServletRequest 失败");
                throw new CloudServiceException("获取HttpServletRequest失败.");
            }

            String bucket;
            if (ObjectUtils.isEmpty(request.getBucket())) {
                bucket = storageProperty.getBucket();
            } else {
                bucket = request.getBucket();
            }
            // 检测 bucket 是否存在
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
                // 创建桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }

            // 根据当前请求的 HttpMethod 来判断是上传还是下载还是删除
            HttpMethod httpMethod = HttpMethod.valueOf(httpRequest.getMethod());
            Http.Method method;
            if (httpMethod == HttpMethod.PUT) {
                // 上传文件
                method = Http.Method.PUT;
            } else if (httpMethod == HttpMethod.DELETE) {
                // 删除文件
                method = Http.Method.DELETE;
            } else if (httpMethod == HttpMethod.GET) {
                // 下载文件
                method = Http.Method.GET;
            } else {
                log.error("不支持的请求方法: {}", httpMethod);
                throw new CloudServiceException("不支持的请求方法: " + httpMethod);
            }

            // 根据传入的桶和文件名组装参数
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                    .method(method)
                    .object(request.getName())
                    // 默认7天有效期(最大7天)
                    .expiry(request.getExpireTimes() == null ? 60 * 60 * 24 * 7 : request.getExpireTimes())
                    .bucket(ObjectUtils.isEmpty(bucket) ? storageProperty.getBucket() : bucket)
                    .build();
            // 生成预签名地址
            String preSignedUrl = minioClient.getPresignedObjectUrl(args);

            // 组装返回参数
            FilePreSignedResponse filePreSignedResponse = new FilePreSignedResponse();
            filePreSignedResponse.setName(request.getName());
            filePreSignedResponse.setUrl(preSignedUrl);
            filePreSignedResponse.setBucket(bucket);
            return filePreSignedResponse;
        } catch (Exception e) {
            log.error("获取预签名失败，原因：{}", e.getMessage(), e);
            throw new CloudServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteByFileUrl(String fileUrl) {
        // 空值判断
        if (ObjectUtils.isEmpty(fileUrl)) {
            return;
        }

        try {
            MinioObject minioObject = parseMinioObject(fileUrl);
            if (minioObject == null) {
                return;
            }

            // 调用MinIO删除旧文件
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minioObject.bucketName())
                            .object(minioObject.objectName())
                            .build()
            );

            log.info("文件【{}】删除成功.", fileUrl);
        } catch (Exception e) {
            // 删除失败不影响主流程（打印日志即可）
            log.warn("文件【{}】删除失败, 原因: {}", fileUrl, e.getMessage());
        }
    }

    /**
     * 兼容历史完整 URL 以及当前去掉 MinIO base url 后的相对路径。
     */
    private MinioObject parseMinioObject(String fileUrl) {
        String path = fileUrl.trim();
        path = removeConfiguredBaseUrl(path, storageProperty.getProxyEndpoint());
        if (!ObjectUtils.isEmpty(storageProperty.getProxyEndpoints())) {
            for (String proxyEndpoint : storageProperty.getProxyEndpoints().values()) {
                path = removeConfiguredBaseUrl(path, proxyEndpoint);
            }
        }
        path = removeConfiguredBaseUrl(path, storageProperty.getEndpoint());
        path = extractPath(path);

        if (ObjectUtils.isEmpty(path)) {
            return null;
        }

        while (path.startsWith("/")) {
            path = path.substring(1);
        }

        int separatorIndex = path.indexOf("/");
        if (separatorIndex <= 0 || separatorIndex == path.length() - 1) {
            return null;
        }

        String bucketName = path.substring(0, separatorIndex);
        String objectName = path.substring(separatorIndex + 1);
        if (ObjectUtils.isEmpty(bucketName) || ObjectUtils.isEmpty(objectName)) {
            return null;
        }

        return new MinioObject(bucketName, objectName);
    }

    private String removeConfiguredBaseUrl(String fileUrl, String baseUrl) {
        if (ObjectUtils.isEmpty(fileUrl) || ObjectUtils.isEmpty(baseUrl)) {
            return fileUrl;
        }

        String normalizedBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        if (fileUrl.startsWith(normalizedBaseUrl)) {
            return fileUrl.substring(normalizedBaseUrl.length());
        }
        if (fileUrl.equals(baseUrl)) {
            return "";
        }
        return fileUrl;
    }

    private String extractPath(String fileUrl) {
        try {
            URI uri = new URI(fileUrl);
            String path = uri.getPath();
            return path == null ? "" : path;
        } catch (URISyntaxException e) {
            int queryIndex = fileUrl.indexOf("?");
            int fragmentIndex = fileUrl.indexOf("#");
            int endIndex = fileUrl.length();
            if (queryIndex >= 0) {
                endIndex = queryIndex;
            }
            if (fragmentIndex >= 0) {
                endIndex = Math.min(endIndex, fragmentIndex);
            }
            return fileUrl.substring(0, endIndex);
        }
    }

    private record MinioObject(String bucketName, String objectName) {
    }
}

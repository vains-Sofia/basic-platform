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
            // 1. 解析URL
            URI uri = new URI(fileUrl);
            // 截取路径部分：/桶名/对象路径
            String path = uri.getPath();
            // 分割路径（示例：/user-avatar/a/b.png → [, user-avatar, a, b.png]）
            String[] pathParts = path.split("/", 3);

            if (pathParts.length < 3) {
                return;
            }

            // 2. 提取桶名 + 对象名
            String bucketName = pathParts[1];
            String objectName = pathParts[2];

            // 3. 调用MinIO删除旧文件
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );

            log.info("文件【{}】删除成功.", fileUrl);
        } catch (Exception e) {
            // 删除失败不影响主流程（打印日志即可）
            log.warn("文件【{}】删除失败, 原因: {}", fileUrl, e.getMessage());
        }
    }
}

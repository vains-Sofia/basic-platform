package com.basic.service;

import com.basic.domain.request.FilePreSignedRequest;
import com.basic.domain.response.FilePreSignedResponse;

/**
 * 文件相关 Service 接口
 *
 * @author vains
 */
public interface FileService {

    /**
     * 文件预签名
     *
     * @param request 文件预签名入参
     * @return 文件预签名响应
     */
    FilePreSignedResponse filePreSigned(FilePreSignedRequest request);
}

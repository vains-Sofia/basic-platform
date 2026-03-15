package com.basic.controller.common;

import com.basic.domain.Result;
import com.basic.domain.request.FilePreSignedRequest;
import com.basic.domain.response.FilePreSignedResponse;
import com.basic.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 文件接口
 *
 * @author vains
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Tag(name = "文件接口", description = "文件接口")
public class FileController {

    private final FileService fileService;

    @PutMapping("/pre/signed")
    @Operation(summary = "文件上传预签名", description = "文件上传预签名")
    public Result<FilePreSignedResponse> uploadPreSigned(@Valid @RequestBody FilePreSignedRequest request) {
        FilePreSignedResponse response = fileService.filePreSigned(request);
        return Result.success(response);
    }

    @GetMapping("/pre/signed")
    @Operation(summary = "文件下载预签名", description = "文件下载预签名")
    public Result<FilePreSignedResponse> downloadPreSigned(@Valid FilePreSignedRequest request) {
        FilePreSignedResponse response = fileService.filePreSigned(request);
        return Result.success(response);
    }

    @DeleteMapping("/pre/signed")
    @Operation(summary = "删除文件预签名", description = "删除文件预签名")
    public Result<FilePreSignedResponse> deletePreSigned(@Valid FilePreSignedRequest request) {
        FilePreSignedResponse response = fileService.filePreSigned(request);
        return Result.success(response);
    }

}

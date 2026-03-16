package com.basic.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件预签名入参
 *
 * @author vains
 */
@Data
@Schema(name = "FilePreSignedRequest", description = "文件预签名入参")
public class FilePreSignedRequest implements Serializable {

    @NotBlank
    @Schema(title = "文件名", description = "文件名.UUID.后缀名")
    private String name;

    @Schema(title = "文件所在的存储桶", description = "默认在basic-cloud的存储桶下")
    private String bucket;

    @Schema(title = "文件预签名过期时间", description = "单位秒，默认604800秒(7天)")
    private Integer expireTimes;

}

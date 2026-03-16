package com.basic.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 发送邮件参数
 *
 * @author vains
 */
@Data
@Schema(name = "MailSenderRequest", description = "发送邮件参数")
public class MailSenderRequest implements Serializable {

    /**
     * 发送者
     */
    @Schema(title = "发送者")
    private String from;

    /**
     * 接收者
     */
    @NotEmpty
    @Schema(title = "接收者")
    private Set<String> mailTo;

    /**
     * 邮件主题
     */
    @NotBlank
    @Schema(title = "邮件主题")
    private String subject;

    /**
     * 邮件内容
     */
    @NotBlank
    @Schema(title = "邮件内容")
    private String content;

    /**
     * 邮件内容是否为 html
     */
    @Schema(title = "邮件内容是否为 html")
    private Boolean contentIsHtml = false;

    /**
     * 附件
     */
    @Schema(title = "附件")
    private List<MultipartFile> attachments;

}

package com.basic.service.impl;

import com.basic.constant.PlatformConstants;
import com.basic.domain.request.MailSenderRequest;
import com.basic.exception.CloudServiceException;
import com.basic.service.CommonService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.mail.autoconfigure.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

/**
 * 公共通用接口 Service 实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final JavaMailSender mailSender;

    private final MailProperties mailProperties;

    @Override
    public void mailSender(MailSenderRequest request) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            // 是否包含附件
            boolean hasMultipart = !ObjectUtils.isEmpty(request.getAttachments());
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, hasMultipart, StandardCharsets.UTF_8.name());

            // 发送人
            String from = ObjectUtils.isEmpty(request.getFrom()) ? PlatformConstants.PLATFORM_NAME : request.getFrom();
            mimeMessageHelper.setFrom(from + "<" + mailProperties.getUsername() + ">");
            // 收件人
            mimeMessageHelper.setTo(request.getMailTo().toArray(new String[0]));
            // 主题
            mimeMessageHelper.setSubject(request.getSubject());
            // 设置内容
            mimeMessageHelper.setText(request.getContent(), request.getContentIsHtml());
            // 添加附件
            if (hasMultipart) {
                for (MultipartFile attachment : request.getAttachments()) {
                    if (attachment == null || ObjectUtils.isEmpty(attachment.getOriginalFilename())) {
                        continue;
                    }
                    mimeMessageHelper.addAttachment(attachment.getOriginalFilename(), attachment);
                }
            }
            mailSender.send(mimeMessage);
            log.info("{} Send email to {}, Subject {}", request.getFrom(), String.join(",", request.getMailTo()), request.getSubject());
        } catch (Exception e) {
            log.error("邮件发送失败，原因：{}", e.getMessage(), e);
            throw new CloudServiceException(e.getMessage());
        }
    }

}

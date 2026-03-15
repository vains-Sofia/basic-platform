package com.basic.controller.login;

import com.basic.constant.PlatformConstants;
import com.basic.domain.Result;
import com.basic.domain.request.MailSenderRequest;
import com.basic.service.CaptchaService;
import com.basic.service.CommonService;
import com.basic.util.RandomUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 验证码接口
 *
 * @author vains
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/captcha")
@Tag(name = "验证码接口", description = "验证码接口")
public class CaptchaController {

    private final CommonService commonService;

    private final CaptchaService captchaService;

    @GetMapping("/getEmailCaptcha")
    @Parameter(name = "email", description = "验证码将发送至该邮箱中.")
    @Operation(summary = "获取邮件验证码", description = "获取邮件验证码")
    public Result<String> getEmailCaptcha(@Email String email) {
        // 随机字符串长度
        int randomLength = 4;
        String captcha = RandomUtils.randomNumber(randomLength);

        MailSenderRequest request = new MailSenderRequest();
        request.setMailTo(Set.of(email));
        request.setFrom(PlatformConstants.PLATFORM_NAME);
        request.setSubject("Verification Code");
        request.setContent("Your verification code is: " + captcha);

        commonService.mailSender(request);

        log.info("Send email to {}, Verification Code {}", email, captcha);

        this.captchaService.save(captcha);
        return Result.success("获取验证码成功.");
    }

}

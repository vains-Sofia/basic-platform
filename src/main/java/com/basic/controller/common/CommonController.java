package com.basic.controller.common;

import com.basic.domain.Result;
import com.basic.domain.request.MailSenderRequest;
import com.basic.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用功能接口
 *
 * @author vains
 */

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
@Tag(name = "通用功能接口", description = "通用功能接口")
public class CommonController {

    private final CommonService commonService;

    @PostMapping("/email/sender")
    @Operation(summary = "邮件发送", description = "邮件发送")
    public Result<String> mailSender(@Valid @RequestBody MailSenderRequest request) {
        commonService.mailSender(request);
        return Result.success();
    }

}

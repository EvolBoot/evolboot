package org.evolboot.captcha.remote.emailcaptcha;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.captcha.domain.emailcaptcha.entity.EmailCaptcha;
import org.evolboot.captcha.domain.emailcaptcha.EmailCaptchaAppService;
import org.evolboot.captcha.domain.emailcaptcha.service.EmailCaptchaCreateFactory;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.IpUtil;
import org.evolboot.shared.email.EmailMessageTag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author evol
 */
@RestController
@RequestMapping("/v1/api/email-captcha")
@Tag(name = "验证码", description = "验证码")
@ApiClient
public class AppEmailCaptchaResourceV1 {

    private final EmailCaptchaAppService appService;

    public AppEmailCaptchaResourceV1(EmailCaptchaAppService appService) {
        this.appService = appService;
    }

    //    @PostMapping("")
//    @Operation(summary = "邮箱-获取验证码")
    public ResponseModel<EmailCaptchaLoginResponse> create(
            @RequestBody
            EmailCaptchaLoginRequest request, HttpServletRequest httpServletRequest
    ) {
        EmailCaptcha captcha = appService.create(request.to(IpUtil.getClientIP(httpServletRequest)));
        return ResponseModel.ok(EmailCaptchaLoginResponse.of(captcha));
    }


    @Operation(summary = "通用邮箱验证码(需传邮箱)")
    @PostMapping("")
    public ResponseModel<EmailCaptchaLoginResponse> sendEmailCaptcha(
            HttpServletRequest servletRequest,
            @RequestBody @Valid AppEmailCaptchaRequest request) {
        EmailCaptcha emailCaptcha = appService.create(new EmailCaptchaCreateFactory.Request(
                request.getEmail(),
                EmailMessageTag.CHECK,
                null,
                null,
                false,
                IpUtil.getClientIP(servletRequest),
                null
        ));
        return ResponseModel.ok(EmailCaptchaLoginResponse.of(emailCaptcha));
    }

}

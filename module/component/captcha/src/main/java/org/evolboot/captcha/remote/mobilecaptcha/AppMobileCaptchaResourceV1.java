package org.evolboot.captcha.remote.mobilecaptcha;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.captcha.domain.mobilecaptcha.entity.MobileCaptcha;
import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptchaAppService;
import org.evolboot.captcha.domain.mobilecaptcha.service.MobileCaptchaCreateFactory;
import org.evolboot.captcha.remote.mobilecaptcha.dto.AppSmsCaptchaRequest;
import org.evolboot.captcha.remote.mobilecaptcha.dto.MobileCaptchaLoginResponse;
import org.evolboot.captcha.remote.mobilecaptcha.dto.MobileCaptchaRequest;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.IpUtil;
import org.evolboot.shared.sms.SmsMessageTag;
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
@RequestMapping("/v1/api/mobile-captcha")
@Tag(name = "验证码", description = "验证码")
@ApiClient
public class AppMobileCaptchaResourceV1 {

    private final MobileCaptchaAppService appService;

    public AppMobileCaptchaResourceV1(MobileCaptchaAppService appService) {
        this.appService = appService;
    }

    //    @PostMapping("")
//    @Operation(summary = "获取短信验证码")
    public ResponseModel<MobileCaptchaLoginResponse> create(
            @RequestBody
            MobileCaptchaRequest request, HttpServletRequest httpServletRequest
    ) {
        MobileCaptcha mobileCaptcha = appService.create(request.to(IpUtil.getClientIP(httpServletRequest)));
        return ResponseModel.ok(MobileCaptchaLoginResponse.of(mobileCaptcha));
    }


    @Operation(summary = "通用短信验证码(需传手机号)")
    @PostMapping("")
    public ResponseModel<MobileCaptchaLoginResponse> smsCaptcha(
            HttpServletRequest servletRequest,
            @RequestBody @Valid
            AppSmsCaptchaRequest request
    ) {
        MobileCaptcha mobileCaptcha = appService.create(new MobileCaptchaCreateFactory.Request(
                request.getPrefix(),
                request.getMobile(),
                SmsMessageTag.CHECK,
                null,
                null,
                false,
                IpUtil.getClientIP(servletRequest),
                null
        ));
        return ResponseModel.ok(MobileCaptchaLoginResponse.of(mobileCaptcha));
    }


}

package org.evolboot.captcha.domain.mobilecaptcha.service;

import org.evolboot.captcha.CaptchaI18nMessage;
import org.evolboot.captcha.acl.client.CaptchaSmsClient;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaAppService;
import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptcha;
import org.evolboot.captcha.domain.mobilecaptcha.repository.MobileCaptchaRepository;
import org.evolboot.core.exception.DomainException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.CodeGeneraterUtil;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.sms.MessageTag;
import org.evolboot.sms.domain.SmsSender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

import static org.evolboot.captcha.CaptchaI18nMessage.ImageCaptcha.CODE_ERROR;
import static org.evolboot.captcha.CaptchaI18nMessage.MobileCaptcha.*;


/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class MobileCaptchaCreateFactory {

    private final MobileCaptchaRepository repository;

    private final CaptchaSmsClient captchaSmsClient;

    private final ImageCaptchaAppService imageCaptchaAppService;


    public MobileCaptchaCreateFactory(MobileCaptchaRepository repository, CaptchaSmsClient captchaSmsClient, ImageCaptchaAppService imageCaptchaAppService) {
        this.repository = repository;
        this.captchaSmsClient = captchaSmsClient;
        this.imageCaptchaAppService = imageCaptchaAppService;
    }

    public MobileCaptcha create(Request request) {
        Assert.notBlank(request.getMobile(),         I18NMessageHolder.message(MOBILE_NOT_BLANK));
        if (request.getVerifyImageCaptcha()) {
            imageCaptchaAppService.verifyIsTrue(request.getImageCaptchaToken(), request.getImageCaptchaCode());
        }
//        Assert.isTrue(RegexUtil.checkMobile(mobile), CaptchaI18nMessage.MobileCaptcha.regexError());
        AtomicReference<String> code = new AtomicReference<>(CodeGeneraterUtil.get6Number());
        repository.findByMobile(request.getMobile()).ifPresent(mobileCaptcha -> {
            if (!mobileCaptcha.isAllowGetNext()) {
                throw new DomainException(I18NMessageHolder.message(INTERVAL_TIME_NOT_YET));
            }
            repository.deleteByToken(mobileCaptcha.getToken());
            code.set(mobileCaptcha.getCode());
        });
        String codeStr = code.get();


        // 发送短信
//        SmsSender.Response response = captchaSmsPort.sendSms(request.getMessageTag(), request.getMobilePrefix(), request.getMobile(), codeStr);

        //TODO 测试
        codeStr = "10086";

        SmsSender.Response response = SmsSender.Response.builder().sendStatus(true).smsContent(codeStr).build();

        if (response.isSendStatus()) {
            MobileCaptcha captcha = MobileCaptcha.builder()
                    .mobilePrefix(request.getMobilePrefix())
                    .mobile(request.getMobile())
                    .code(codeStr)
                    .messageTag(request.getMessageTag())
                    .smsContent(response.getSmsContent())
                    .ip(request.getIp())
                    .internalCode(request.getInternalCode())
                    .build();
            repository.save(captcha);
            return captcha;
        }
        throw new DomainException(I18NMessageHolder.message(SEND_FAILURE));
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String mobilePrefix;
        private String mobile;
        private MessageTag messageTag;
        private String imageCaptchaToken;
        private String imageCaptchaCode;
        private Boolean verifyImageCaptcha;
        private String ip;
        private String internalCode;

        public Boolean getVerifyImageCaptcha() {
            return ExtendObjects.requireNonNullElse(verifyImageCaptcha, true);
        }
    }


}

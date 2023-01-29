package org.evolboot.captcha.domain.emailcaptcha;

import org.evolboot.captcha.CaptchaI18nMessage;
import org.evolboot.captcha.domain.emailcaptcha.repository.EmailCaptchaRepository;
import org.evolboot.captcha.domain.emailcaptcha.sender.EmailCodePort;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaAppService;
import org.evolboot.core.exception.DomainRepetitionException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.CodeGeneraterUtil;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.RegexUtil;
import org.evolboot.shared.email.MessageTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author evol
 * 
 */
@Service
public class EmailCaptchaCreateFactory {

    private final ImageCaptchaAppService imageCaptchaAppService;


    private final EmailCaptchaRepository repository;

    private final EmailCodePort sender;

    public EmailCaptchaCreateFactory(ImageCaptchaAppService imageCaptchaAppService, EmailCaptchaRepository repository, EmailCodePort sender) {
        this.imageCaptchaAppService = imageCaptchaAppService;
        this.repository = repository;
        this.sender = sender;
    }

    public EmailCaptcha create(Request request) {
        Assert.notBlank(request.getEmail(), CaptchaI18nMessage.EmailCaptcha.notBlank());
        Assert.isTrue(RegexUtil.checkEmail(request.getEmail()), CaptchaI18nMessage.EmailCaptcha.regexError());
        if (request.getVerifyImageCaptcha()) {
            imageCaptchaAppService.verifyIsTrue(request.getImageCaptchaToken(), request.getImageCaptchaCode());
        }
        AtomicReference<String> code = new AtomicReference<>(CodeGeneraterUtil.get6Number());
        repository.findByEmail(request.getEmail()).ifPresent(emailCaptcha -> {
            if (!emailCaptcha.isAllowGetNext()) {
                throw new DomainRepetitionException(CaptchaI18nMessage.EmailCaptcha.intervalTimeNotYet());
            }
            repository.deleteByToken(emailCaptcha.getToken());
            code.set(emailCaptcha.getCode());
        });
        String codeStr = code.get();
        codeStr = "10086";
//        sender.send(email, codeStr);
        EmailCaptcha captcha = EmailCaptcha.builder()
                .email(request.getEmail())
                .code(codeStr)
                .messageTag(request.getMessageTag())
                .ip(request.getIp())
                .build();
        repository.save(captcha);
        return captcha;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String email;
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

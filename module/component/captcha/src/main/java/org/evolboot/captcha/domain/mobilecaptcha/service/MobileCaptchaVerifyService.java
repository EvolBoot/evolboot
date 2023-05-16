package org.evolboot.captcha.domain.mobilecaptcha.service;

import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptcha;
import org.evolboot.captcha.domain.mobilecaptcha.repository.MobileCaptchaRepository;
import org.evolboot.core.exception.DomainException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.springframework.stereotype.Service;

import static org.evolboot.captcha.CaptchaI18nMessage.MobileCaptcha.CODE_ERROR;

/**
 * @author evol
 */
@Service
public class MobileCaptchaVerifyService {

    private final MobileCaptchaRepository repository;


    public MobileCaptchaVerifyService(MobileCaptchaRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String mobilePrefix, String mobile, String token, String code, String internalCode) {
        MobileCaptcha mobileCaptcha = repository.findById(token).orElseThrow(() -> new DomainException(I18NMessageHolder.message(CODE_ERROR)));
        if (mobileCaptcha.isTimeout()) {
            return false;
        }
        boolean result = mobileCaptcha.verify(mobilePrefix, mobile, code, internalCode);
        repository.save(mobileCaptcha);
        return result;
    }
}

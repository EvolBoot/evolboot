package org.evolboot.captcha.domain.mobilecaptcha;

import org.evolboot.captcha.CaptchaI18nMessage;
import org.evolboot.captcha.domain.mobilecaptcha.repository.MobileCaptchaRepository;
import org.evolboot.core.exception.DomainException;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
public class MobileCaptchaVerifyService {

    private final MobileCaptchaRepository repository;


    public MobileCaptchaVerifyService(MobileCaptchaRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String mobilePrefix, String mobile, String token, String code, String internalCode) {
        MobileCaptcha mobileCaptcha = repository.findById(token).orElseThrow(() -> new DomainException(CaptchaI18nMessage.MobileCaptcha.codeError()));
        if (mobileCaptcha.isTimeout()) {
            return false;
        }
        boolean result = mobileCaptcha.verify(mobilePrefix, mobile, code, internalCode);
        repository.save(mobileCaptcha);
        return result;
    }
}

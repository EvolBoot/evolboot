package org.evolboot.captcha.domain.emailcaptcha;

import org.evolboot.captcha.CaptchaI18nMessage;
import org.evolboot.captcha.domain.emailcaptcha.repository.EmailCaptchaRepository;
import org.evolboot.core.exception.DomainException;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
public class EmailCaptchaVerifyService {

    private final EmailCaptchaRepository repository;

    public EmailCaptchaVerifyService(EmailCaptchaRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String token, String email, String code, String internalCode) {
        EmailCaptcha emailCaptcha = repository.findById(token).orElseThrow(() -> new DomainException(CaptchaI18nMessage.EmailCaptcha.codeError()));
        if (emailCaptcha.isTimeout()) {
            return false;
        }
        boolean result = emailCaptcha.verify(email, code, internalCode);
        repository.save(emailCaptcha);
        return result;
    }
}

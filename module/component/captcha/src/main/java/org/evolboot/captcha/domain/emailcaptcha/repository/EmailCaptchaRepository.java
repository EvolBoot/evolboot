package org.evolboot.captcha.domain.emailcaptcha.repository;

import org.evolboot.captcha.domain.emailcaptcha.EmailCaptcha;
import org.evolboot.core.data.BaseRepository;

import java.util.Optional;

/**
 * @author evol
 */
public interface EmailCaptchaRepository extends BaseRepository<EmailCaptcha, Long> {

    EmailCaptcha save(EmailCaptcha emailCaptcha);

    Optional<EmailCaptcha> findById(String token);

    Optional<EmailCaptcha> findByEmail(String email);

    void deleteByToken(String token);

}

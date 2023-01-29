package org.evolboot.captcha.domain.mobilecaptcha.repository;

import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptcha;

import java.util.Optional;

/**
 * @author evol
 * 
 */
public interface MobileCaptchaRepository {

    MobileCaptcha save(MobileCaptcha mobileCaptcha);

    Optional<MobileCaptcha> findById(String token);

    void deleteByToken(String token);

    Optional<MobileCaptcha> findByMobile(String mobile);


}

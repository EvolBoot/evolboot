package org.evolboot.captcha.domain.mobilecaptcha.repository;

import org.evolboot.captcha.domain.mobilecaptcha.entity.MobileCaptcha;
import org.evolboot.core.data.BaseRepository;

import java.util.Optional;

/**
 * @author evol
 */
public interface MobileCaptchaRepository extends BaseRepository<MobileCaptcha, Long> {

    MobileCaptcha save(MobileCaptcha mobileCaptcha);

    Optional<MobileCaptcha> findById(String token);

    void deleteByToken(String token);

    Optional<MobileCaptcha> findByMobile(String mobile);


}

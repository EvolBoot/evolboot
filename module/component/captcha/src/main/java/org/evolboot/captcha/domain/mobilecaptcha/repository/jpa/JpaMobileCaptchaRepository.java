package org.evolboot.captcha.domain.mobilecaptcha.repository.jpa;

import org.evolboot.captcha.domain.mobilecaptcha.entity.MobileCaptcha;
import org.evolboot.captcha.domain.mobilecaptcha.repository.MobileCaptchaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author evol
 */
@NoRepositoryBean
public interface JpaMobileCaptchaRepository extends MobileCaptchaRepository, JpaRepository<MobileCaptcha, String> {


}

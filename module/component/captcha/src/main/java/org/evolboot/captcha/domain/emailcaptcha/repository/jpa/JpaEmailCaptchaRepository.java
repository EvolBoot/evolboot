package org.evolboot.captcha.domain.emailcaptcha.repository.jpa;

import org.evolboot.captcha.domain.emailcaptcha.entity.EmailCaptcha;
import org.evolboot.captcha.domain.emailcaptcha.repository.EmailCaptchaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author evol
 */
@NoRepositoryBean
public interface JpaEmailCaptchaRepository extends EmailCaptchaRepository, JpaRepository<EmailCaptcha, String> {

}

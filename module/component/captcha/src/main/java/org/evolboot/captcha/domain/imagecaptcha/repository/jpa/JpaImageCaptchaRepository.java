package org.evolboot.captcha.domain.imagecaptcha.repository.jpa;

import org.evolboot.captcha.domain.imagecaptcha.ImageCaptcha;
import org.evolboot.captcha.domain.imagecaptcha.repository.ImageCaptchaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author evol
 */
@NoRepositoryBean
public interface JpaImageCaptchaRepository extends ImageCaptchaRepository, JpaRepository<ImageCaptcha, String> {

    @Override
    default void deleteByToken(String token) {
        //TODO 上线时删除此方法
    }
}

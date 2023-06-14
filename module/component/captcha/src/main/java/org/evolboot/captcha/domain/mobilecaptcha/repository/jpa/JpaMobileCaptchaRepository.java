package org.evolboot.captcha.domain.mobilecaptcha.repository.jpa;

import com.querydsl.jpa.JPQLQuery;
import org.evolboot.captcha.autoconfigure.MobileCaptchaProperties;
import org.evolboot.captcha.domain.mobilecaptcha.entity.MobileCaptcha;
import org.evolboot.captcha.domain.mobilecaptcha.entity.QMobileCaptcha;
import org.evolboot.captcha.domain.mobilecaptcha.repository.MobileCaptchaRepository;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

import static org.evolboot.captcha.autoconfigure.MobileCaptchaProperties.CONDITIONAL_ON_PROPERTY_TYPE;

/**
 * @author evol
 */
@ConditionalOnProperty(prefix = MobileCaptchaProperties.CONFIGURATION_PREFIX, name = CONDITIONAL_ON_PROPERTY_TYPE, havingValue = "jpa")
public interface JpaMobileCaptchaRepository extends MobileCaptchaRepository, ExtendedQuerydslPredicateExecutor<MobileCaptcha, String>, JpaRepository<MobileCaptcha, String> {

    @Override
    default Optional<MobileCaptcha> findByMobile(String mobile) {
        QMobileCaptcha q = QMobileCaptcha.mobileCaptcha;
        JPQLQuery<MobileCaptcha> jpqlQuery = getJPQLQuery();
        return Optional.ofNullable(jpqlQuery.select(q).from(q).where(q.mobile.eq(mobile)).orderBy(q.createAt.desc()).fetchFirst());
    }
}

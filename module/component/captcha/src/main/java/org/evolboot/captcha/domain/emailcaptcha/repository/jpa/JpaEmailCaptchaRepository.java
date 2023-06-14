package org.evolboot.captcha.domain.emailcaptcha.repository.jpa;

import com.querydsl.jpa.JPQLQuery;
import org.evolboot.captcha.autoconfigure.EmailCaptchaProperties;
import org.evolboot.captcha.domain.emailcaptcha.entity.EmailCaptcha;
import org.evolboot.captcha.domain.emailcaptcha.entity.QEmailCaptcha;
import org.evolboot.captcha.domain.emailcaptcha.repository.EmailCaptchaRepository;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.evolboot.captcha.autoconfigure.EmailCaptchaProperties.CONDITIONAL_ON_PROPERTY_TYPE;

/**
 * @author evol
 */
@Repository
@ConditionalOnProperty(prefix = EmailCaptchaProperties.CONFIGURATION_PREFIX, name = CONDITIONAL_ON_PROPERTY_TYPE, havingValue = "jpa")
public interface JpaEmailCaptchaRepository extends EmailCaptchaRepository, ExtendedQuerydslPredicateExecutor<EmailCaptcha, String>, JpaRepository<EmailCaptcha, String> {


    @Override
    default Optional<EmailCaptcha> findByEmail(String email) {
        QEmailCaptcha q = QEmailCaptcha.emailCaptcha;
        JPQLQuery<EmailCaptcha> jpqlQuery = getJPQLQuery();
        return Optional.ofNullable(jpqlQuery.select(q).from(q).where(q.email.eq(email)).orderBy(q.createAt.desc()).fetchFirst());
    }

}

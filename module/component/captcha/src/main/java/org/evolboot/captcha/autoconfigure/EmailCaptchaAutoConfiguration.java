package org.evolboot.captcha.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.captcha.domain.emailcaptcha.EmailCaptchaConfiguration;
import org.evolboot.captcha.domain.emailcaptcha.repository.EmailCaptchaRepository;
import org.evolboot.captcha.domain.emailcaptcha.repository.jpa.JpaEmailCaptchaRepository;
import org.evolboot.captcha.domain.emailcaptcha.repository.redis.EmailCaptchaRedisTemplate;
import org.evolboot.captcha.domain.emailcaptcha.repository.redis.RedisEmailCaptchaRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import jakarta.persistence.EntityManager;

import static org.evolboot.captcha.autoconfigure.EmailCaptchaProperties.CONDITIONAL_ON_PROPERTY_TYPE;

/**
 * @author evol
 */
@EnableConfigurationProperties(EmailCaptchaProperties.class)
@Configuration
@Slf4j
public class EmailCaptchaAutoConfiguration {


    private final EmailCaptchaProperties emailCaptchaProperties;

    public EmailCaptchaAutoConfiguration(EmailCaptchaProperties emailCaptchaProperties) {
        this.emailCaptchaProperties = emailCaptchaProperties;
        EmailCaptchaConfiguration.setValue(
                EmailCaptchaConfiguration.Value.builder()
                        .expires(emailCaptchaProperties.getTimeout())
                        .limitVerifyCount(emailCaptchaProperties.getLimitVerifyCount())
                        .build()
        );
        log.info("配置邮件验证码:{}", EmailCaptchaConfiguration.getValue());
    }

    @Bean
    @ConditionalOnClass(RedisEmailCaptchaRepository.class)
    @ConditionalOnProperty(prefix = EmailCaptchaProperties.CONFIGURATION_PREFIX, name = CONDITIONAL_ON_PROPERTY_TYPE, havingValue = "redis")
    public EmailCaptchaRepository redisEmailCaptchaRepository(
            RedisConnectionFactory redisConnectionFactory
    ) {
        log.info("配置邮件验证码: 使用 redis 存储");
        EmailCaptchaRedisTemplate redisTemplate = new EmailCaptchaRedisTemplate(redisConnectionFactory);
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        return new RedisEmailCaptchaRepository(redisTemplate, stringRedisTemplate);
    }

/*
    @Bean
    @ConditionalOnClass(JpaEmailCaptchaRepository.class)
    @ConditionalOnProperty(prefix = EmailCaptchaProperties.CONFIGURATION_PREFIX, name = CONDITIONAL_ON_PROPERTY_TYPE, havingValue = "jpa")
    public EmailCaptchaRepository jpaEmailCaptchaRepository(EntityManager entityManager) {
        log.info("配置邮件验证码: 使用 jpa 存储");
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(JpaEmailCaptchaRepository.class);
    }*/

}

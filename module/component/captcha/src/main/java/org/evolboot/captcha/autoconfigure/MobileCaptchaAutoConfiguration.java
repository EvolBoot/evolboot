package org.evolboot.captcha.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptchaConfiguration;
import org.evolboot.captcha.domain.mobilecaptcha.repository.MobileCaptchaRepository;
import org.evolboot.captcha.domain.mobilecaptcha.repository.jpa.JpaMobileCaptchaRepository;
import org.evolboot.captcha.domain.mobilecaptcha.repository.redis.MobileCaptchaRedisTemplate;
import org.evolboot.captcha.domain.mobilecaptcha.repository.redis.RedisMobileCaptchaRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.persistence.EntityManager;

/**
 * @author evol
 */
@EnableConfigurationProperties(MobileCaptchaProperties.class)
@Configuration
@Slf4j
public class MobileCaptchaAutoConfiguration {

    private final static String CONDITIONAL_ON_PROPERTY_TYPE = "repository-type";

    private final MobileCaptchaProperties mobileCaptchaProperties;

    public MobileCaptchaAutoConfiguration(MobileCaptchaProperties mobileCaptchaProperties) {
        this.mobileCaptchaProperties = mobileCaptchaProperties;
        MobileCaptchaConfiguration.setValue(
                MobileCaptchaConfiguration.Value.builder()
                        .expires(mobileCaptchaProperties.getTimeout())
                        .limitVerifyCount(mobileCaptchaProperties.getLimitVerifyCount())
                        .build()
        );
        log.info("配置手机验证码: {}", MobileCaptchaConfiguration.getValue());
    }

    @Bean
    @ConditionalOnClass(RedisMobileCaptchaRepository.class)
    @ConditionalOnProperty(prefix = MobileCaptchaProperties.CONFIGURATION_PREFIX, name = CONDITIONAL_ON_PROPERTY_TYPE, havingValue = "redis")
    public MobileCaptchaRepository redisMobileCaptchaRepository(
            RedisConnectionFactory redisConnectionFactory
    ) {
        log.info("配置手机验证码: 使用 redis 存储");
        MobileCaptchaRedisTemplate redisTemplate = new MobileCaptchaRedisTemplate(redisConnectionFactory);
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        return new RedisMobileCaptchaRepository(redisTemplate, stringRedisTemplate);
    }

    @Bean
    @ConditionalOnClass(JpaMobileCaptchaRepository.class)
    @ConditionalOnProperty(prefix = MobileCaptchaProperties.CONFIGURATION_PREFIX, name = CONDITIONAL_ON_PROPERTY_TYPE, havingValue = "jpa")
    public MobileCaptchaRepository jpaMobileCaptchaRepository(EntityManager entityManager) {
        log.info("配置手机验证码: 使用 jpa 存储");
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(JpaMobileCaptchaRepository.class);
    }

}

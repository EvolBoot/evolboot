package org.evolboot.captcha.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaConfiguration;
import org.evolboot.captcha.domain.imagecaptcha.repository.ImageCaptchaRepository;
import org.evolboot.captcha.domain.imagecaptcha.repository.jpa.JpaImageCaptchaRepository;
import org.evolboot.captcha.domain.imagecaptcha.repository.redis.ImageCaptchaRedisTemplate;
import org.evolboot.captcha.domain.imagecaptcha.repository.redis.RedisImageCaptchaRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import jakarta.persistence.EntityManager;

/**
 * @author evol
 */
@EnableConfigurationProperties(ImageCaptchaProperties.class)
@Configuration
@Slf4j
public class ImageCaptchaAutoConfiguration {

    private final static String CONDITIONAL_ON_PROPERTY_TYPE = "repository-type";

    private final ImageCaptchaProperties imageCaptchaProperties;

    public ImageCaptchaAutoConfiguration(ImageCaptchaProperties imageCaptchaProperties) {
        this.imageCaptchaProperties = imageCaptchaProperties;
        ImageCaptchaConfiguration.setValue(
                ImageCaptchaConfiguration.Value.builder()
                        .defaultExpires(imageCaptchaProperties.getTimeout())
                        .build()
        );
        log.info("配置图片验证码: {}", ImageCaptchaConfiguration.getValue());
    }

    @Bean
    @ConditionalOnMissingBean(ImageCaptchaRepository.class)
    @ConditionalOnClass(RedisImageCaptchaRepository.class)
    @ConditionalOnProperty(prefix = ImageCaptchaProperties.CONFIGURATION_PREFIX, name = CONDITIONAL_ON_PROPERTY_TYPE, havingValue = "redis")
    public ImageCaptchaRepository redisImageCaptchaRepository(
            RedisConnectionFactory redisConnectionFactory
    ) {
        log.info("配置图片验证码: 使用 redis 存储");
        ImageCaptchaRedisTemplate redisTemplate = new ImageCaptchaRedisTemplate(redisConnectionFactory);
        return new RedisImageCaptchaRepository(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(ImageCaptchaRepository.class)
    @ConditionalOnClass(JpaImageCaptchaRepository.class)
    @ConditionalOnProperty(prefix = ImageCaptchaProperties.CONFIGURATION_PREFIX, name = CONDITIONAL_ON_PROPERTY_TYPE, havingValue = "jpa")
    public ImageCaptchaRepository jpaImageCaptchaRepository(EntityManager entityManager) {
        log.info("配置图片验证码: 使用 jpa 存储");
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(JpaImageCaptchaRepository.class);
    }

}

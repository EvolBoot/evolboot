package org.evolboot.captcha.domain.emailcaptcha.repository.redis;

import org.evolboot.captcha.domain.emailcaptcha.entity.EmailCaptcha;
import org.evolboot.captcha.domain.emailcaptcha.repository.EmailCaptchaRepository;
import org.evolboot.shared.cache.RedisCacheName;
import org.springframework.data.redis.core.StringRedisTemplate;

import jakarta.persistence.Transient;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author evol
 */
public class RedisEmailCaptchaRepository implements EmailCaptchaRepository {

    private final EmailCaptchaRedisTemplate redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    @Transient
    public final static String TOKEN_PREFIX = RedisCacheName.EMAIL_CAPTCHA_CACHE_KEY;

    public RedisEmailCaptchaRepository(EmailCaptchaRedisTemplate redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public EmailCaptcha save(EmailCaptcha emailCaptcha) {
        String redisKey = getRedisKey(emailCaptcha.getToken());
        redisTemplate.boundValueOps(redisKey).set(emailCaptcha, emailCaptcha.getRemainExpires(), TimeUnit.MILLISECONDS);
        String emailRedisKey = getRedisKey(emailCaptcha.getEmail());
        stringRedisTemplate.boundValueOps(emailRedisKey).set(emailCaptcha.getToken(), emailCaptcha.getRemainExpires(), TimeUnit.MILLISECONDS);
        return emailCaptcha;
    }

    @Override
    public Optional<EmailCaptcha> findById(String token) {
        EmailCaptcha emailCaptcha = redisTemplate.boundValueOps(getRedisKey(token)).get();
        return Optional.ofNullable(emailCaptcha);
    }

    @Override
    public Optional<EmailCaptcha> findByEmail(String email) {
        String token = stringRedisTemplate.boundValueOps(getRedisKey(email)).get();
        if (token == null) {
            return Optional.empty();
        }
        String redisKey = getRedisKey(token);
        EmailCaptcha emailCaptcha = redisTemplate.boundValueOps(redisKey).get();
        return Optional.ofNullable(emailCaptcha);
    }

    @Override
    public void deleteByToken(String token) {
        String redisKey = getRedisKey(token);
        findById(redisKey).ifPresent(mobileCaptcha -> {
            stringRedisTemplate.delete(getRedisKey(mobileCaptcha.getEmail()));
            redisTemplate.delete(redisKey);
        });
    }

    private String getRedisKey(String token) {
        return TOKEN_PREFIX + "_" + token;
    }

}

package org.evolboot.captcha.domain.mobilecaptcha.repository.redis;

import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptcha;
import org.evolboot.captcha.domain.mobilecaptcha.repository.MobileCaptchaRepository;
import org.evolboot.shared.cache.RedisCacheName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author evol
 */
@Slf4j
public class RedisMobileCaptchaRepository implements MobileCaptchaRepository {

    private final MobileCaptchaRedisTemplate redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public final static String TOKEN_PREFIX = RedisCacheName.MOBILE_CAPTCHA_CACHE_KEY;


    public RedisMobileCaptchaRepository(MobileCaptchaRedisTemplate redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public MobileCaptcha save(MobileCaptcha mobileCaptcha) {
        String redisKey = getRedisKey(mobileCaptcha.getToken());
        redisTemplate.boundValueOps(redisKey).set(mobileCaptcha, mobileCaptcha.getRemainExpires(), TimeUnit.MILLISECONDS);
        String mobileRedisKey = getRedisKey(mobileCaptcha.getMobile());
        stringRedisTemplate.boundValueOps(mobileRedisKey).set(mobileCaptcha.getToken(), mobileCaptcha.getRemainExpires(), TimeUnit.MILLISECONDS);
        return mobileCaptcha;
    }

    @Override
    public Optional<MobileCaptcha> findById(String token) {
        String redisKey = getRedisKey(token);
        MobileCaptcha mobileCaptcha = redisTemplate.boundValueOps(redisKey).get();
        return Optional.ofNullable(mobileCaptcha);
    }


    @Override
    public void deleteByToken(String token) {
        String redisKey = getRedisKey(token);
        findById(redisKey).ifPresent(mobileCaptcha -> {
            String mobileRedisKey = getRedisKey(mobileCaptcha.getMobile());
            stringRedisTemplate.delete(mobileRedisKey);
            redisTemplate.delete(redisKey);
        });
    }

    @Override
    public Optional<MobileCaptcha> findByMobile(String mobile) {
        String mobileRedisKey = getRedisKey(mobile);
        String token = stringRedisTemplate.boundValueOps(mobileRedisKey).get();
        if (token == null) {
            return Optional.empty();
        }
        String redisKey = getRedisKey(token);
        MobileCaptcha mobileCaptcha = redisTemplate.boundValueOps(redisKey).get();
        return Optional.ofNullable(mobileCaptcha);
    }

    private String getRedisKey(String key) {
        return TOKEN_PREFIX + "_" + key;
    }

}

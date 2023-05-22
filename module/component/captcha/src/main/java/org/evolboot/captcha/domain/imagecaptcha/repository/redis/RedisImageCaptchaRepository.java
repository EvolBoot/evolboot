package org.evolboot.captcha.domain.imagecaptcha.repository.redis;

import org.evolboot.captcha.domain.imagecaptcha.entity.ImageCaptcha;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaConfiguration;
import org.evolboot.captcha.domain.imagecaptcha.repository.ImageCaptchaRepository;
import org.evolboot.shared.cache.RedisCacheName;

import java.util.concurrent.TimeUnit;

/**
 * @author evol
 */
public class RedisImageCaptchaRepository implements ImageCaptchaRepository {

    public final static String TOKEN_PREFIX = RedisCacheName.IMAGE_CAPTCHA_CACHE_KEY;

    private final ImageCaptchaRedisTemplate redisTemplate;

    public RedisImageCaptchaRepository(ImageCaptchaRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public ImageCaptcha save(ImageCaptcha imageCaptcha) {
        String redisKey = getRedisKey(imageCaptcha.getToken());
        redisTemplate.boundValueOps(redisKey).set(imageCaptcha, ImageCaptchaConfiguration.getValue().getDefaultExpires(), TimeUnit.MILLISECONDS);
        return imageCaptcha;
    }

    @Override
    public ImageCaptcha findByToken(String token) {
        String redisKey = getRedisKey(token);
        return redisTemplate.boundValueOps(redisKey).get();
    }

    @Override
    public void deleteByToken(String token) {
        String redisKey = getRedisKey(token);
        redisTemplate.delete(redisKey);
    }

    private String getRedisKey(String token) {
        return TOKEN_PREFIX + "_" + token;
    }
}

package org.evolboot.security.api.repository.redis;

import org.evolboot.security.api.EvolSession;
import org.evolboot.security.api.repository.EvolSessionRepository;
import org.evolboot.shared.cache.RedisCacheName;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author evol
 */
public class RedisEvolSessionRepository implements EvolSessionRepository {


    public final static String TOKEN_PREFIX = RedisCacheName.ACCESS_TOKEN_PREFIX;

    private final EvolSessionRedisTemplate redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    private final long timeout;

    public RedisEvolSessionRepository(EvolSessionRedisTemplate redisTemplate, StringRedisTemplate stringRedisTemplate, long timeout) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
        this.timeout = timeout;
    }

    @Override
    public String save(String token, EvolSession evolSession) {
        String redisKey = getUserIdRedisKey(evolSession.getPrincipalId().toString());
        redisTemplate.boundValueOps(redisKey).set(evolSession, timeout, TimeUnit.MILLISECONDS);
        String tokenRedisKey = getTokenRedisKey(token);
        stringRedisTemplate.boundValueOps(tokenRedisKey).set(evolSession.getPrincipalId().toString(), timeout, TimeUnit.MILLISECONDS);
        return token;
    }

    @Override
    public EvolSession findByToken(String token) {
        String tokenRedisKey = getTokenRedisKey(token);
        String userId = stringRedisTemplate.boundValueOps(tokenRedisKey).get();
        if (userId != null) {
            String userIdRedisKey = getUserIdRedisKey(userId);
            EvolSession evolSession = redisTemplate.boundValueOps(userIdRedisKey).get();
            if (evolSession == null) {
                stringRedisTemplate.delete(tokenRedisKey);
            }
            return evolSession;
        }
        return null;
    }

    @Override
    public EvolSession findByUserId(Long userId) {
        return redisTemplate.boundValueOps(getUserIdRedisKey(userId.toString())).get();
    }

    @Override
    public void deleteByToken(String token) {
        String tokenRedisKey = getTokenRedisKey(token);
        String userId = stringRedisTemplate.boundValueOps(tokenRedisKey).get();
        if (userId == null) {
            return;
        }
        String userIdRedisKey = getUserIdRedisKey(userId);
        EvolSession evolSession = redisTemplate.boundValueOps(userIdRedisKey).get();
        if (evolSession == null) {
            return;
        }
        evolSession.getDevices().remove(token);
        if (evolSession.getDevices().isEmpty()) {
            redisTemplate.delete(userIdRedisKey);
        } else {
            redisTemplate.boundValueOps(userIdRedisKey).set(evolSession, timeout, TimeUnit.MILLISECONDS);
        }
        stringRedisTemplate.delete(tokenRedisKey);
    }

    @Override
    public void deleteByUserId(Long userId) {
        String userIdRedisKey = getUserIdRedisKey(userId.toString());
        EvolSession evolSession = redisTemplate.boundValueOps(userIdRedisKey).get();
        if (evolSession == null) {
            return;
        }
        evolSession.getDevices().forEach((key, value) -> {
            stringRedisTemplate.delete(key);
        });
        redisTemplate.delete(userIdRedisKey);
    }

    @Override
    public void refresh(String token) {
        String tokenRedisKey = getTokenRedisKey(token);
        String userId = stringRedisTemplate.boundValueOps(tokenRedisKey).get();
        if (userId == null) {
            return;
        }
        String userIdRedisKey = getUserIdRedisKey(userId);
        EvolSession evolSession = redisTemplate.boundValueOps(userIdRedisKey).get();
        if (evolSession == null) {
            redisTemplate.delete(userIdRedisKey);
            stringRedisTemplate.delete(tokenRedisKey);
        } else {
            stringRedisTemplate.boundValueOps(tokenRedisKey).set(userId, timeout, TimeUnit.MILLISECONDS);
            redisTemplate.boundValueOps(userIdRedisKey).set(evolSession, timeout, TimeUnit.MILLISECONDS);
        }

    }


    private String getTokenRedisKey(String token) {
        return TOKEN_PREFIX + "_token_" + token;
    }


    private String getUserIdRedisKey(String userId) {
        return TOKEN_PREFIX + "_userid_" + userId;
    }

}

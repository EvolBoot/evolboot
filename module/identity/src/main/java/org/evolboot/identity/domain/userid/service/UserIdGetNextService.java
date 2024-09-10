package org.evolboot.identity.domain.userid.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.service.RedissonTemplate;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import org.evolboot.shared.cache.RedisCacheName;
import org.redisson.api.RDeque;
import org.redisson.api.RSet;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserIdGetNextService {

    private final List<Long> userIds = Lists.newArrayList();
    private int warnNum = 500;
    private int step = 1000;

    private final UserIdRepository repository;

    private final UserIdSupportService supportService;


    private final RedissonTemplate redissonTemplate;

    private final RDeque<Long> deque;

    private final RSet<Long> uniqueUserIds;


    protected UserIdGetNextService(UserIdRepository repository, UserIdSupportService supportService, RedissonTemplate redissonTemplate) {
        this.repository = repository;
        this.supportService = supportService;
        this.redissonTemplate = redissonTemplate;
        this.deque = redissonTemplate.getDeque(RedisCacheName.IDENTITY_USER_ID_PREFIX + "_DEQUE");  // 获取 Redis 双端队列
        this.uniqueUserIds = redissonTemplate.getSet(RedisCacheName.IDENTITY_USER_ID_PREFIX + "_SET");  // 用于唯一性检查的 Redis Set
    }

    public void checkAndAddCache() {
        if (uniqueUserIds.size() < warnNum) {
            log.info("UserId数量为:{},增加:{}", uniqueUserIds.size(), step);
            List<Long> _userIds = repository.rand(step);
            for (Long userId : _userIds) {
                if (uniqueUserIds.add(userId)) {
                    deque.add(userId);
                }
            }
        }
        log.info("UserId数量:{}", uniqueUserIds.size());
    }

    public Long next() {
        synchronized (deque) {
            // 请稍等
            Assert.isTrue(uniqueUserIds.size() > 0, IdentityI18nMessage.UserId.pleaseWait());
            Long userId = deque.pollFirst();  // 移除并返回队列的第一个元素
            if (userId != null) {
                uniqueUserIds.remove(userId);  // 同时从 Set 中移除该元素
            }
            return userId;
        }
    }
}
package org.evolboot.identity.domain.userid;

import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class UserIdGetNextService extends UserIdSupportService {

    private final List<Long> userIds = Lists.newArrayList();
    private int warnNum = 500;
    private int step = 1000;

    protected UserIdGetNextService(UserIdRepository repository) {
        super(repository);
    }

    public void checkAndAddCache() {
        if (userIds.size() < warnNum) {
            log.info("UserId数量为:{},增加:{}", userIds.size(), step);
            userIds.addAll(repository.rand(step));
            log.info("UserId数量:{}", userIds.size());
        }
    }

    public Long next() {
        synchronized (userIds) {
            // 请稍等
            Assert.isTrue(userIds.size() > 0, IdentityI18nMessage.UserId.pleaseWait());
            Long userId = userIds.get(0);
            userIds.remove(0);
            return userId;
        }
    }
}

package org.evolboot.identity.domain.user.service;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.password.ReversiblePassword;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.shared.lang.CurrentPrincipal;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class UserPasswordSetService {

    private final UserRepository repository;

    public UserPasswordSetService(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(CurrentPrincipal currentPrincipal, Long userId, ReversiblePassword password) {
        User user = repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));

        // 如果 currentPrincipal 存在且有 tenantId，则验证用户必须属于同一租户
        if (ExtendObjects.nonNull(currentPrincipal) && ExtendObjects.nonNull(currentPrincipal.getTenantId())) {
            Assert.isTrue(currentPrincipal.getTenantId().equals(user.getTenantId()), "无权重置其他租户的用户密码");
        }

        user.resetPassword(password.toOriginalPassword());
        repository.save(user);
    }
}

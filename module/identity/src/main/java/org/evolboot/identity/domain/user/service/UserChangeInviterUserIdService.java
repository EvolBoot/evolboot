package org.evolboot.identity.domain.user.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.relation.RelationAppService;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * 更换邀请人
 *
 * @author evol
 */
@Service
@Slf4j
public class UserChangeInviterUserIdService {

    private final UserRepository repository;

    private final UserSupportService supportService;
    private final RelationAppService relationAppService;

    public UserChangeInviterUserIdService(UserRepository repository, UserSupportService supportService, RelationAppService relationAppService) {
        this.repository = repository;
        this.supportService = supportService;
        this.relationAppService = relationAppService;
    }

    public void execute(Long userId, Long newInviterUserId) {
        User user = supportService.findById(userId);
        repository.findById(newInviterUserId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.inviterDoesNotExist()));
        user.changeInviterUserId(newInviterUserId);
        repository.save(user);
        // 移动关系表
        relationAppService.moveTree(userId, newInviterUserId);
    }

}

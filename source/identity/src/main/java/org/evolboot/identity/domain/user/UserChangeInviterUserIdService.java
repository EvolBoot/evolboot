package org.evolboot.identity.domain.user;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.relation.RelationAppService;
import org.evolboot.identity.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 更换邀请人
 *
 * @author evol
 * 
 */
@Service
@Slf4j
public class UserChangeInviterUserIdService extends UserSupportService {

    private final RelationAppService relationAppService;

    public UserChangeInviterUserIdService(UserRepository repository, RelationAppService relationAppService) {
        super(repository);
        this.relationAppService = relationAppService;
    }

    public void execute(Long userId, Long newInviterUserId) {
        User user = findById(userId);
        repository.findById(newInviterUserId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.inviterDoesNotExist()));
        user.changeInviterUserId(newInviterUserId);
        repository.save(user);
        // 移动关系表
        relationAppService.moveTree(userId, newInviterUserId);
    }

}

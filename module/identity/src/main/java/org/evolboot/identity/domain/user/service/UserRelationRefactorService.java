package org.evolboot.identity.domain.user.service;

import org.evolboot.identity.domain.user.relation.RelationAppService;
import org.evolboot.identity.domain.user.repository.UserIdAndInviterUserId;
import org.evolboot.identity.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户关系重构，数据发生错误时维护，需要停止APP端
 *
 * @author evol
 * 
 */
@Service
@Slf4j
public class UserRelationRefactorService {

    private final UserRepository repository;
    private final RelationAppService relationAppService;

    public UserRelationRefactorService(UserRepository repository, RelationAppService relationAppService) {
        this.repository = repository;
        this.relationAppService = relationAppService;
    }

    public void execute() {
        List<UserIdAndInviterUserId> userIdAndInviterUserIds = repository.findIdAndInviterUserId();
        relationAppService.deleteAll();
        userIdAndInviterUserIds.forEach(userIdAndInviterUserId -> {
            relationAppService.moveTree(userIdAndInviterUserId.getId(), userIdAndInviterUserId.getInviterUserId());
        });
    }
}

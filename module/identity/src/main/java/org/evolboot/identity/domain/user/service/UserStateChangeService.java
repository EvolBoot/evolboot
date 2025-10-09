package org.evolboot.identity.domain.user.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.acl.client.SecurityAccessTokenClient;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.entity.UserState;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.shared.lang.CurrentPrincipal;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserStateChangeService {


    private final UserRepository repository;

    private final UserSupportService supportService;

    private final SecurityAccessTokenClient securityAccessTokenClient;

    public UserStateChangeService(UserRepository repository, UserSupportService supportService, SecurityAccessTokenClient securityAccessTokenClient) {
        this.repository = repository;
        this.supportService = supportService;
        this.securityAccessTokenClient = securityAccessTokenClient;
    }

    public void execute(CurrentPrincipal currentPrincipal, Request request) {
        User user = supportService.findById(request.getId());

        // 如果 currentPrincipal 存在且有 tenantId，则验证用户必须属于同一租户
        if (ExtendObjects.nonNull(currentPrincipal) && ExtendObjects.nonNull(currentPrincipal.getTenantId())) {
            Assert.isTrue(currentPrincipal.getTenantId().equals(user.getTenantId()), "无权修改其他租户的用户状态");
        }

        user.setState(request.getState());
        if (UserState.LOCK.equals(request.getState())) {
            securityAccessTokenClient.kickOut(request.getId());
        }
        repository.save(user);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        private Long id;

        private UserState state;

    }
}



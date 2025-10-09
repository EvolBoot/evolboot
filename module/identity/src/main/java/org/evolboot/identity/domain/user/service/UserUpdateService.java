package org.evolboot.identity.domain.user.service;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.service.crypto.rsa.RsaService;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.role.service.RoleQueryService;
import org.evolboot.identity.domain.user.entity.Gender;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.entity.UserState;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.shared.lang.CurrentPrincipal;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author evol
 */
@Service
@Slf4j
//TODO 多语言
public class UserUpdateService {


    private final UserRepository repository;

    private final UserSupportService supportService;
    private final RsaService rsaService;
    private final RoleQueryService roleQueryService;

    public UserUpdateService(UserRepository repository, UserSupportService supportService, RsaService rsaService, RoleQueryService roleQueryService) {
        this.repository = repository;
        this.supportService = supportService;
        this.rsaService = rsaService;
        this.roleQueryService = roleQueryService;
    }


    public void execute(CurrentPrincipal currentPrincipal, Request request) {
        User user = repository.findById(request.getId()).orElseThrow(() -> new DomainNotFoundException(
                IdentityI18nMessage.User.userNotFound()
        ));

        // 如果 currentPrincipal 存在且有 tenantId，则验证用户必须属于同一租户
        if (ExtendObjects.nonNull(currentPrincipal) && ExtendObjects.nonNull(currentPrincipal.getTenantId())) {
            Assert.isTrue(currentPrincipal.getTenantId().equals(user.getTenantId()), "无权修改其他租户的用户");
        }

        user.setAvatar(request.getAvatar());
        user.setNickname(request.getNickname());
        user.setRemark(request.getRemark());

        if (ExtendObjects.nonNull(request.getGender())) {
            user.setGender(request.getGender());
        }

        if (ExtendObjects.isNotBlank(request.getPassword())) {
            user.resetPassword(rsaService.privateDecryptStr(request.getPassword()));
        }

        if (ExtendObjects.isNotBlank(request.getUsername())) {
            repository.findByUsername(request.getUsername().trim()).ifPresent(_user -> {
                Assert.isTrue(_user.id().equals(user.id()), "用户名已存在");
            });
            user.setUsername(request.getUsername().trim());
        }

        if (ExtendObjects.isNotBlank(request.getMobile())) {
//            Assert.notBlank(request.getMobilePrefix(), "手机号区号不能为空");
            repository.findByMobile(request.getMobile().trim()).ifPresent(mobileUser -> {
                Assert.isTrue(mobileUser.id().equals(user.id()), "手机号已被占用");
            });
            user.setMobile(request.getMobile(), request.getMobilePrefix());
        } else {
            user.setMobile("", "");
        }

        if (ExtendObjects.isNotBlank(request.getEmail())) {
            repository.findByEmail(request.getEmail().trim()).ifPresent(emailUser -> {
                Assert.isTrue(emailUser.id().equals(user.id()), "邮箱已被占用");
            });
            user.setEmail(request.getEmail().trim());
        } else {
            user.setEmail("");
        }

        if (ExtendObjects.nonNull(request.getState())) {
            user.setState(request.getState());
        }
        // [] 也是值，如果没这个都没有，说明不改
        if (request.getRoleId() != null) {
            roleQueryService.mustExist(request.getRoleId());
            user.updateRoleId(request.getRoleId());
        }

        repository.save(user);

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Request {
        private Long id;
        private String username;
        private String nickname;
        private String avatar;
        private Gender gender;
        private UserState state;
        private Set<Long> roleId;
        private String password;
        private String email;
        private String mobile;
        private String mobilePrefix;
        private String remark;
    }


}

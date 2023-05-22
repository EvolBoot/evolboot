package org.evolboot.identity.domain.user.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.UserConfiguration;
import org.evolboot.identity.domain.user.entity.UserType;
import org.evolboot.identity.domain.user.password.ReversiblePassword;
import org.evolboot.identity.domain.user.password.UserEncryptPasswordService;
import org.evolboot.identity.domain.user.relation.RelationAppService;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.identity.domain.userid.UserIdAppService;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
import org.evolboot.shared.event.user.UserCreatedEvent;
import org.evolboot.shared.lang.DeviceType;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserCreateFactory extends UserSupportService {

    private final EventPublisher eventPublisher;
    private final UserEncryptPasswordService userEncryptPasswordService;
    private final RelationAppService relationAppService;
    private final UserIdAppService userIdAppService;
    private final UserRoleAppService userRoleAppService;


    public UserCreateFactory(UserRepository repository, EventPublisher eventPublisher, UserEncryptPasswordService userEncryptPasswordService, RelationAppService relationAppService, UserIdAppService userIdAppService, UserRoleAppService userRoleAppService) {
        super(repository);
        this.eventPublisher = eventPublisher;
        this.userEncryptPasswordService = userEncryptPasswordService;
        this.relationAppService = relationAppService;
        this.userIdAppService = userIdAppService;
        this.userRoleAppService = userRoleAppService;
    }


    public User create(Request request) {
        if (ExtendObjects.nonNull(request.getInviterUserId()) && request.getInviterUserId() > 0) {
            repository.findById(request.getInviterUserId()).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.inviterDoesNotExist()));
        }

        Assert.isTrue(
                ExtendObjects.isNotBlank(request.getEmail())
                        || ExtendObjects.isNotBlank(request.getUsername())
                        || ExtendObjects.isNotBlank(request.getMobile())
                ,
                "用户名,邮箱,和手机号,必填一个"
        );

        if (ExtendObjects.isNotBlank(request.getEmail())) {
            requireEmailIsNoExist(request.getEmail());
        }

        if (ExtendObjects.isNotBlank(request.getUsername())) {
            requireUsernameIsNoExist(request.getUsername());
        }

        if (ExtendObjects.isNotBlank(request.getMobile())) {
            requireMobileIsNoExist(request.getMobile());
        }

        Long id = userIdAppService.getNextUserId();

        String OriginalPassword;
        if (ExtendObjects.isBlank(request.getEncodePassword())) {
            //TODO 如果密码为空,则产生一个随机密码,需要改
            OriginalPassword = UUID.randomUUID().toString();
        } else {
            ReversiblePassword reversiblePassword = userEncryptPasswordService.toReversiblePassword(request.getEncodePassword());
            OriginalPassword = reversiblePassword.toOriginalPassword();
        }
        User user = User.builder()
                .id(id)
                .username(request.getUsername())
                .email(request.getEmail())
                .mobilePrefix(request.getMobilePrefix())
                .mobile(request.getMobile())
                .password(OriginalPassword)
                .avatar(UserConfiguration.getValue().getDefaultAvatar())
                .userIdentity(request.getUserIdentity())
                .inviterUserId(request.getInviterUserId())
                .userType(request.getUserType())
                .registerIp(request.getRegisterIp())
                .remark(request.getRemark())
                .build();

        if (!ExtendObjects.isEmpty(request.getRoles()) && UserIdentity.ROLE_STAFF.equals(request.getUserIdentity())) {
            userRoleAppService.updateRole(id, request.getRoles());
        }
        repository.save(user);
        relationAppService.create(request.getInviterUserId(), user.id());
        eventPublisher.publishEvent(new UserCreatedEvent(user.id(), user.getUserIdentity()));
        return user;
    }


    @Getter
    @Builder
    @Setter
    @AllArgsConstructor
    public static class Request {
        private String username;
        private String mobilePrefix;
        private String mobile;
        private String email;
        private String encodePassword;
        private String encodeFundsPassword;
        private UserIdentity userIdentity;
        private Long inviterUserId;
        private UserType userType;
        private String registerIp;
        private String remark;
        private String nickname;
        private DeviceType deviceType;
        private Set<Long> roles;

        public String getUsername() {
            return ExtendObjects.trimToNull(username);
        }

        public String getMobilePrefix() {
            return ExtendObjects.trimToNull(mobilePrefix);
        }

        public String getMobile() {
            return ExtendObjects.trimToNull(mobile);
        }

        public String getEmail() {
            return ExtendObjects.trimToNull(email);
        }
    }

}

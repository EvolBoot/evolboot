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
import org.evolboot.identity.domain.role.service.RoleQueryService;
import org.evolboot.identity.domain.user.UserConfiguration;
import org.evolboot.identity.domain.user.entity.Gender;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.entity.UserState;
import org.evolboot.identity.domain.user.entity.UserType;
import org.evolboot.identity.domain.user.password.ReversiblePassword;
import org.evolboot.identity.domain.user.password.UserEncryptPasswordService;
import org.evolboot.identity.domain.user.relation.service.RelationCreateService;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.identity.domain.userid.service.UserIdGetNextService;
import org.evolboot.shared.event.user.UserCreatedEvent;
import org.evolboot.shared.lang.DeviceType;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.stereotype.Service;

import java.util.List;
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
    /**
     * 用户上下级关联
     */
    private final RelationCreateService relationCreateService;
    /**
     * 用户ID
     */
    private final UserIdGetNextService userIdGetNextService;

    /**
     * 角色查询
     */
    private final RoleQueryService roleQueryService;


    public UserCreateFactory(UserRepository repository, EventPublisher eventPublisher, UserEncryptPasswordService userEncryptPasswordService, RelationCreateService relationCreateService, UserIdGetNextService userIdGetNextService, RoleQueryService roleQueryService) {
        super(repository);
        this.eventPublisher = eventPublisher;
        this.userEncryptPasswordService = userEncryptPasswordService;
        this.relationCreateService = relationCreateService;
        this.userIdGetNextService = userIdGetNextService;
        this.roleQueryService = roleQueryService;
    }


    /**
     * 注册主流程
     *
     * @param request
     * @return
     */
    public User create(Request request) {

        // 检查邀请人是否存在
        checkInviterUserId(request.getInviterUserId());

        // 检查邮箱，手机号，用户名等唯一性
        checkEmailOrMobileOrUsername(request);

        Long id = userIdGetNextService.next();

        String originalPassword;
        //TODO 如果密码为空,则产生一个随机密码,需要改
        if (ExtendObjects.isBlank(request.getEncodePassword())) {
            originalPassword = UUID.randomUUID().toString();
        } else {
            ReversiblePassword reversiblePassword = userEncryptPasswordService.toReversiblePassword(request.getEncodePassword());
            originalPassword = reversiblePassword.toOriginalPassword();
        }
        // 创建用户
        User user = User.builder()
                .id(id)
                .username(request.getUsername())
                .email(request.getEmail())
                .mobilePrefix(request.getMobilePrefix())
                .mobile(request.getMobile())
                .password(originalPassword)
                .avatar(UserConfiguration.getValue().getDefaultAvatar())
                .userIdentity(request.getUserIdentity())
                .inviterUserId(request.getInviterUserId())
                .userType(request.getUserType())
                .registerIp(request.getRegisterIp())
                .remark(request.getRemark())
                .gender(request.getGender())
                .state(request.getState())
                .build();

        // 如果是员工，且存在角色信息,则更新
        if (UserIdentity.ROLE_STAFF.equals(request.getUserIdentity()) && !ExtendObjects.isEmpty(request.getRoleId())) {
            roleQueryService.mustExist(request.getRoleId());
//            List<Role> roles = roleQueryService.findAllById(request.getRoles());
//            Assert.isTrue(roles.size() == request.getRoles().size(), "不存在的角色信息");
//            userRoleUpdateService.execute(id, request.getRoles());
            user.addRoleId(request.getRoleId());
        }

        repository.save(user);
        relationCreateService.execute(request.getInviterUserId(), user.id());
        eventPublisher.publishEvent(new UserCreatedEvent(user.id(), user.getUserIdentity()));
        return user;
    }


    /**
     * 检查邀请人是否存在
     *
     * @param inviterUserId
     */
    private void checkInviterUserId(Long inviterUserId) {
        //  检查邀请人
        if (ExtendObjects.nonNull(inviterUserId) && inviterUserId > 0) {
            repository.findById(inviterUserId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.inviterDoesNotExist()));
        }
    }

    /**
     * 检查邮箱，手机号，用户名等唯一性
     *
     * @param request
     */
    private void checkEmailOrMobileOrUsername(Request request) {

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
    }


    /**
     * 请求信息
     */
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
        private UserState state;
        private String registerIp;
        private String remark;
        private String nickname;
        private DeviceType deviceType;
        private Gender gender;
        private Set<Long> roleId;

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

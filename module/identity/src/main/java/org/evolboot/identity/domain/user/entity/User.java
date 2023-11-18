package org.evolboot.identity.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.data.jpa.convert.LongSetConverter;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.DelState;
import org.evolboot.core.i18n.I18NMessageAssert;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.UserConfiguration;
import org.evolboot.identity.domain.user.repository.jpa.convert.UserIdentitySetConverterForUserType;
import org.evolboot.shared.lang.UserIdentity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.*;

/**
 * @author evol
 */
@Entity
@Table(name = "evoltb_identity_user")
@Getter
@Slf4j
@NoArgsConstructor
public class User extends JpaAbstractEntity<Long> implements AggregateRoot<User> {

    @Id
    private Long id;

    private DelState delState = DelState.ACTIVE;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "手机号前缀")
    private String mobilePrefix;

    @Schema(description = "头像")
    private String avatar;

    @JsonIgnore
    @Schema(description = "密码")
    @Embedded
    private ImmutablePassword password;

    /**
     * 用户性别
     */
    private Gender gender = Gender.UNKNOWN;

    /**
     * 用户状态
     */
    private UserState state = UserState.ACTIVE;

    /**
     * 用户类比（测试用户，正常用户）
     */
    private UserType userType = UserType.NORMAL;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 身份
     */
    @Type(UserIdentitySetConverterForUserType.class)
    private Set<UserIdentity> userIdentity = Sets.newHashSet();

    /**
     * 上级ID
     * 邀请人ID
     */
    private Long inviterUserId;

    private String registerIp;

    private String loginIp;

    private Date lastLoginTime;

    private String googleAuthSecret;

    private Boolean enableGoogleAuth = false;

    private String remark;

    @JsonIgnore
    @Schema(description = "安全密码")
    @Embedded
    private ImmutableSecurityPassword securityPassword;

    @Convert(converter = LongSetConverter.class)
    private Set<Long> roleId = Sets.newHashSet();

    @Builder
    public User(
            Long id,
            String username,
            String mobilePrefix,
            String mobile,
            String email,
            String password,
            String avatar,
            UserIdentity userIdentity,
            Gender gender,
            UserState state,
            Long inviterUserId,
            UserType userType,
            String registerIp,
            String remark,
            List<Long> roleId
    ) {
        this.id = id;
        setEmail(email);
        setUsername(username);
        setPassword(ImmutablePassword.of(password));
        setAvatar(avatar);
        setState(state);
        setGender(gender);
        addUserIdentity(userIdentity);
        setNickname(username);
        setMobile(mobile, mobilePrefix);
        setUserType(userType);
        setInviterUserId(inviterUserId);
        setRegisterIp(registerIp);
        addRoleId(roleId);
        setRemark(remark);
    }

    public void addRoleId(Collection<Long> roleId) {
        if (!ExtendObjects.isEmpty(roleId)) {
            this.roleId.addAll(roleId);
        }
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void addUserIdentity(UserIdentity userIdentity) {
        if (ExtendObjects.nonNull(userIdentity) && !hasUserIdentity(userIdentity)) {
            this.userIdentity.add(userIdentity);
        }
    }

    public void removeUserIdentity(UserIdentity userIdentity) {
        if (ExtendObjects.nonNull(userIdentity) && hasUserIdentity(userIdentity)) {
            this.userIdentity.remove(userIdentity);
        }
    }

    private void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }


    public void delete() {
        this.delState = DelState.ARCHIVE;
    }

    public void setUserType(UserType userType) {
        if (userType == null) {
            userType = UserType.NORMAL;
        }
        this.userType = userType;
    }


    public void setInviterUserId(Long inviterUserId) {
        this.inviterUserId = inviterUserId;
    }

    public void setGoogleAuthSecret(String newGoogleAuthenticatorSecret) {
        this.googleAuthSecret = newGoogleAuthenticatorSecret;
    }

    /**
     * 判断是否拥有指定身份
     *
     * @param userIdentity
     * @return
     */
    public boolean hasUserIdentity(UserIdentity userIdentity) {
        return this.userIdentity.contains(userIdentity);
    }

    /**
     * 设置性别
     *
     * @param gender
     */
    public void setGender(Gender gender) {
        if (Objects.isNull(gender)) {
            gender = Gender.UNKNOWN;
        }
        this.gender = gender;
    }

    public void changeInviterUserId(Long newInviterUserId) {
        Assert.isTrue(!newInviterUserId.equals(inviterUserId), "邀请人和原来的一致,没有改变");
        this.inviterUserId = newInviterUserId;
    }

    /**
     * 设置头像
     *
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = ExtendObjects.requireNonNullElse(avatar, UserConfiguration.getValue().getDefaultAvatar());
    }


    /**
     * 设置昵称
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private void setSecurityPassword(ImmutableSecurityPassword securityPassword) {
        this.securityPassword = securityPassword;
    }

    /**
     * 密码
     *
     * @param password
     */
    private void setPassword(ImmutablePassword password) {
        this.password = password;
    }

    /**
     * 校验密码
     *
     * @param password
     * @return
     */
    public boolean verifyPassword(String password) {
        return this.password.matches(password);
    }

    /**
     * 重置密码
     *
     * @param password
     */
    public void resetPassword(String password) {
        setPassword(ImmutablePassword.of(password));
    }

    /**
     * 校验密码
     *
     * @param securityPassword
     * @return
     */
    public boolean verifySecurityPassword(String securityPassword) {
        Assert.notBlank(securityPassword, "安全密码未设置");
        return this.securityPassword.matches(securityPassword);
    }


    public void resetSecurityPassword(String securityPassword) {
        setSecurityPassword(ImmutableSecurityPassword.of(securityPassword));
    }


    /**
     * 修改登录密码
     *
     * @param oldPassword
     * @param newPassword
     */
    public void updatePassword(String oldPassword, String newPassword) {
        Assert.isTrue(!oldPassword.equals(newPassword), IdentityI18nMessage.User.oldPasswordAndNewPasswordEquals());
        Assert.isTrue(verifyPassword(oldPassword), IdentityI18nMessage.User.oldPasswordError());
        setPassword(ImmutablePassword.of(newPassword));
    }


    /**
     * 修改安全密码
     *
     * @param oldPassword
     * @param newPassword
     */
    public void updateSecurityPassword(String oldPassword, String newPassword) {
        Assert.isTrue(
                verifySecurityPassword(oldPassword), IdentityI18nMessage.User.oldPasswordError());
        setSecurityPassword(ImmutableSecurityPassword.of(newPassword));
    }


    /**
     * 设置游戏
     *
     * @param email
     */
    public void setEmail(String email) {
        if (ExtendObjects.isNotBlank(email)) {
            this.email = email;
        }
    }

    public void setMobile(String mobile, String mobilePrefix) {
        if (ExtendObjects.isNotBlank(mobile)) {
            this.mobile = mobile.trim();
        }
        if (ExtendObjects.isNotBlank(mobilePrefix)) {
            this.mobilePrefix = mobilePrefix.trim();
        }
    }

    public void setUsername(String username) {
        if (ExtendObjects.isNotBlank(username)) {
            I18NMessageAssert.fieldNotEmpty(IdentityI18nMessage.User.usernameNotEmpty(), username);
//            Assert.isTrue(
//                    UserValidUtil.checkUsername(username),
//                    IdentityI18nMessage.User.usernameValidFail());
            this.username = username.trim().toLowerCase();
        }
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public void active() {
        setState(UserState.ACTIVE);
    }

    public void lock() {
        setState(UserState.LOCK);
    }

    public void removeRoleId() {
        this.roleId = null;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public User root() {
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id);
    }
}

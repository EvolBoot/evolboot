package org.evolboot.bffadmin.domain.response;

import org.evolboot.identity.domain.user.shared.Gender;
import org.evolboot.identity.domain.user.shared.UserStatus;
import org.evolboot.identity.domain.user.shared.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author evol
 * 
 */
@Getter
@Setter
public class BffStaffUser {

    private Long id;

    private String username;

    private String email;

    private String mobilePrefix;

    private String mobile;

    private String avatar;

    private Gender gender = Gender.UNKNOWN;

    private UserStatus status = UserStatus.ACTIVE;

    private String nickname;

    private Date createTime;

    private String registerIp;

    private String loginIp;

    private Date lastLoginTime;

    private UserType userType;

    private String roleName;

    private String roleId;
}

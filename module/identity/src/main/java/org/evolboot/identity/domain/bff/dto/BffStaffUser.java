package org.evolboot.identity.domain.bff.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.identity.domain.user.entity.Gender;
import org.evolboot.identity.domain.user.entity.UserState;
import org.evolboot.identity.domain.user.entity.UserType;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author evol
 */
@Getter
@Setter
@Schema(title = "员工信息")
public class BffStaffUser {

    private Long id;

    private String username;

    private String email;

    private String mobilePrefix;

    private String mobile;

    private String avatar;

    private Gender gender = Gender.UNKNOWN;

    private UserState state = UserState.ACTIVE;

    private String nickname;

    private Date createTime;

    private String registerIp;

    private String loginIp;

    private Date lastLoginTime;

    private UserType userType;

    private String roleName;

    private String roleId;
}

package org.evolboot.bffadmin.domain.response;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.identity.domain.user.Gender;
import org.evolboot.identity.domain.user.UserType;

/**
 * @author evol
 */
@Setter
@Getter
public abstract class BffAbstractUser {


    private Long userId;

    private String username;

    private String nickname;

    private String email;

    private String mobilePrefix;

    private String mobile;

    private String avatar;

    private Gender gender;

    private Long inviterUserId;

    private UserType userType;

}

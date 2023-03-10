package org.evolboot.bffapp.domain;

import org.evolboot.identity.domain.user.Gender;
import org.evolboot.identity.domain.user.UserType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 *
 */
@Setter
@Getter
public class BffUser {

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

package org.evolboot.bff.domain.app.dto;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.identity.domain.user.entity.Gender;
import org.evolboot.identity.domain.user.entity.UserType;

/**
 * @author evol
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

package org.evolboot.identity.remote.user;

import org.evolboot.identity.domain.user.service.UserUpdateService;
import org.evolboot.identity.domain.user.Gender;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 * 
 */
@Setter
@Getter
public class UserUpdateRequest {


    private String nickname;
    private String avatar;
    private Gender gender;

    public UserUpdateService.Request to() {
        return UserUpdateService.Request
                .builder()
                .nickname(nickname)
                .avatar(avatar)
                .gender(gender)
                .build();
    }
}

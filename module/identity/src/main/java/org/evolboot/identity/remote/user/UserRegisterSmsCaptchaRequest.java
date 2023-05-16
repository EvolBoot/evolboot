package org.evolboot.identity.remote.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
public class UserRegisterSmsCaptchaRequest {

    private String prefix;

    private String mobile;


}

package org.evolboot.identity.remote.user;

import org.evolboot.identity.domain.user.shared.UserType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 * 
 */
@Setter
@Getter
public class UpdateUserTypeRequest {
    private UserType userType;
}

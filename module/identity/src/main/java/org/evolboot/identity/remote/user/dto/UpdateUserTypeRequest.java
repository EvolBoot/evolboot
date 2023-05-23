package org.evolboot.identity.remote.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.identity.domain.user.entity.UserType;

/**
 * @author evol
 */
@Setter
@Getter
public class UpdateUserTypeRequest {
    private UserType userType;
}

package org.evolboot.identity.remote.user;

import lombok.Data;

import java.util.Set;

/**
 * @author evol
 * 
 */
@Data
public class UserRoleUpdateRequest {

    Set<Long> roles;

}

package org.evolboot.identity.remote.user.dto;

import lombok.Data;

/**
 * @author evol
 */
@Data
public class UserPasswordSetRequest {
    Long id;
    String password;
}

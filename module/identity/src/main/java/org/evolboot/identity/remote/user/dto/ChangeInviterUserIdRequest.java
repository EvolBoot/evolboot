package org.evolboot.identity.remote.user.dto;

import lombok.Data;

/**
 * @author evol
 */
@Data
public class ChangeInviterUserIdRequest {

    private Long id;

    private Long newInviterUserId;

}

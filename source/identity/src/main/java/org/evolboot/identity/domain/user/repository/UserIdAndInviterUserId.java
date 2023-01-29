package org.evolboot.identity.domain.user.repository;

import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 * 
 */
@Setter
@Getter
public class UserIdAndInviterUserId {

    private Long id;

    private Long inviterUserId;


}

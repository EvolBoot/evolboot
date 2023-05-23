package org.evolboot.identity.remote.role.dto;

import com.google.common.collect.Sets;
import lombok.Data;
import org.evolboot.identity.domain.role.service.RoleCreateFactory;

import java.util.Set;

/**
 *
 */
@Data
public class CreateRoleRequest {

    private String roleName;

    private Set<Long> permissions = Sets.newHashSet();

    public RoleCreateFactory.Request toRequest() {
        return RoleCreateFactory.Request.builder()
                .roleName(getRoleName())
                .permissions(getPermissions())
                .build();
    }
}

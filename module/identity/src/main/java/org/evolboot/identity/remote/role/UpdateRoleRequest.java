package org.evolboot.identity.remote.role;


import com.google.common.collect.Sets;
import lombok.Data;
import org.evolboot.identity.domain.role.service.RoleUpdateService;

import java.util.Set;

/**
 *
 */
@Data
public class UpdateRoleRequest {


    private String roleName;

    private String remark;

    private Set<Long> permissions = Sets.newHashSet();

    public RoleUpdateService.Request toRequest() {

        return RoleUpdateService.Request.builder()
                .roleName(roleName)
                .permissions(permissions)
                .remark(remark)
                .build();
    }

}

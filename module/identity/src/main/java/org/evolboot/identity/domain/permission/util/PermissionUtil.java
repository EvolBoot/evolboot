package org.evolboot.identity.domain.permission.util;

import org.evolboot.identity.domain.permission.entity.Permission;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author evol
 */
public final class PermissionUtil {

    public static List<Permission> convertTree(List<Permission> permissionList) {
        Long defaultParentId = Permission.DEFAULT_PERMISSION_PARENT;
        Map<Long, List<Permission>> permissionMap = permissionList.stream().filter(permission ->
                        !permission.getParentId().equals(defaultParentId))
                .collect(Collectors.groupingBy(Permission::getParentId));
        permissionList.forEach(permission -> permission.setChildren(permissionMap.get(permission.getId())));
        return permissionList.stream().filter(permission -> permission.getParentId().equals(defaultParentId)).collect(Collectors.toList());
    }

}

package org.evolboot.identity.domain.permission.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendHttpUtil;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.IdUtil;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author evol
 */
@Service
@Slf4j
public class PermissionImportService {

    private final PermissionRepository repository;

    public PermissionImportService(PermissionRepository repository) {
        this.repository = repository;
    }


    public void importJsonData(String url) {
        String data = ExtendHttpUtil.get(url);
        HashMap parse = JsonUtil.parse(data, HashMap.class);
        String data2 = JsonUtil.stringify(parse.get("data"));
        List<Permission> permissions = JsonUtil.parse(data2, List.class, Permission.class);
        saveAll(permissions);
        log.info("data:{}", data);
    }


    private void saveAll(List<Permission> list) {
        for (Permission permission : list) {
            if (ExtendObjects.isNull(permission.getType())) {
                permission.setType(Type.menu);
            }
            if (ExtendObjects.isNull(permission.getSort())) {
                permission.setSort(0);
            }
            if (ExtendObjects.isNull(permission.getId())) {
                permission.setId(IdUtil.nextId());
            }
            if (ExtendObjects.isNull(permission.getIsLink())) {
                permission.setIsLink(false);
            }
            repository.save(permission);
            if (!ExtendObjects.isEmpty(permission.getChildren())) {
                saveAll(permission.getChildren());
            }
        }
    }


}

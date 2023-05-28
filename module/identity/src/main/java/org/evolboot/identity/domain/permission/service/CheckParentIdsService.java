package org.evolboot.identity.domain.permission.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author evol
 */
@Service
@Slf4j
//TODO 多语言
public class CheckParentIdsService {

    private final PermissionRepository repository;

    public CheckParentIdsService(PermissionRepository repository) {
        this.repository = repository;
    }

    public void parentIdExist(List<Long> parentIds) {
        if (ExtendObjects.isEmpty(parentIds)) {
            return;
        }
        for (Long parentId : parentIds) {
            if (parentId != 0) {
                Assert.isTrue(repository.findById(parentId).isPresent(), parentId + "不存在的父类ID");
            }
        }
    }

}

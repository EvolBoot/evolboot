package org.evolboot.identity.domain.user.relation.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.user.relation.Relation;
import org.evolboot.identity.domain.user.relation.repository.RelationRepository;

import java.util.List;

/**
 * 用户上下级关系
 *
 * @author evol
 */
@Slf4j
public abstract class RelationSupportService {

    protected final RelationRepository repository;

    protected RelationSupportService(RelationRepository repository) {
        this.repository = repository;
    }

    protected void saveSelf(Long id) {
        repository.save(new Relation(id, id, 0));
    }

    /**
     * 将指定节点移动到另某节点下面，该方法不修改子节点的相关记录，
     * 为了保证数据的完整性，需要与moveSubTree()方法配合使用。
     *
     * @param id     指定节点id
     * @param parent 某节点id
     */
    protected void moveNode(Long id, Long parent) {
        repository.deleteByDescendant(id);
        repository.insertPath(id, parent);
        saveSelf(id);
    }

    /**
     * 将指定节点的所有子树移动到某节点下
     * 如果两个参数相同，则相当于重建子树，用于父节点移动后更新路径
     *
     * @param id     指定节点id
     * @param parent 某节点id
     */
    protected void moveSubTree(Long id, Long parent) {
        List<Long> descendants = repository.queryDescendantByAncestorAndDistanceIsOne(id);
        descendants.forEach(descendant -> {
            moveNode(descendant, parent);
            moveSubTree(descendant, descendant);
        });
    }

}

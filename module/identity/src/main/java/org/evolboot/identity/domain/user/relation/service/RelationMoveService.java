package org.evolboot.identity.domain.user.relation.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.user.relation.repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 将一个分类移动到目标分类下面（成为其子分类）。被移动分类的子类将自动上浮（成为指定分类
 * 父类的子分类），即使目标是指定分类原本的父类。
 * <p>
 * 例如下图(省略顶级分类)：
 * 1                                     1
 * |                                   / | \
 * 2                                  3  4  5
 * / | \             move(2,7)               / \
 * 3  4  5         --------------->          6   7
 * / \                                 /  / | \
 * 6    7                               8  9  10 2
 * /    /  \
 * 8    9    10
 *
 * @author evol
 */
@Service
@Slf4j
public class RelationMoveService {

    private final RelationRepository repository;
    private final RelationSupportService supportService;


    protected RelationMoveService(RelationRepository repository, RelationSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Long id, Long target) {
        if (id.equals(target)) {
            throw new IllegalArgumentException("不能移动到自己下面");
        }
        Optional<Long> ancestor = repository.queryAncestorByDescendantAndDistance(id, 1);
        supportService.moveSubTree(id, ancestor.get());
        supportService.moveNode(id, target);
    }
}

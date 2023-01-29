package org.evolboot.identity.domain.user.relation;

import org.evolboot.identity.domain.user.relation.repository.RelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 将一个分类移动到目标分类下面（成为其子分类），被移动分类的子分类也会随着移动。
 * 如果目标分类是被移动分类的子类，则先将目标分类（连带子类）移动到被移动分类原来的
 * 的位置，再移动需要被移动的分类。
 * <p>
 * 例如下图(省略顶级分类)：
 * 1                                     1
 * |                                     |
 * 2                                     7
 * / | \           moveTree(2,7)         / | \
 * 3  4  5         --------------->      9  10  2
 * / \                                   / | \
 * 6    7                                 3  4  5
 * /    /  \                                     |
 * 8    9    10                                   6
 * |
 * 8
 *
 * @author evol
 * 
 */
@Service
@Slf4j
public class RelationMoveTreeService extends RelationSupportService {

    protected RelationMoveTreeService(RelationRepository repository) {
        super(repository);
    }

    public void execute(Long id, Long target) {
        /* 移动分移到自己子树下和无关节点下两种情况 */
        Optional<Integer> distance = repository.queryDistanceByAncestorAndDescendant(id, target);
        if (distance.isEmpty()) {
            // 移动到父节点或其他无关系节点，不需要做额外动作
        } else if (distance.get().equals(0)) {
            throw new IllegalArgumentException("不能移动到自己下面");
        } else {
            // 如果移动的目标是其子类，需要先把子类移动到本类的位置

            // 查他的祖先
            Optional<Long> parent = repository.queryAncestorByDescendantAndDistance(id, 1);
            // 目标类是其子类，需要把目标类移动到当前类的父类下
            moveNode(target, parent.get());

            // 把目标类的树也移动过来
            moveSubTree(target, target);
        }
        moveNode(id, target);
        moveSubTree(id, id);
    }
}

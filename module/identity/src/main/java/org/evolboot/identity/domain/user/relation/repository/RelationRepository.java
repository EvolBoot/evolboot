package org.evolboot.identity.domain.user.relation.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.user.relation.Relation;
import org.evolboot.identity.domain.user.relation.dto.RelationQueryRequest;

import java.util.List;
import java.util.Optional;


/**
 * 用户上下级关系
 *
 * @author evol
 */
public interface RelationRepository extends BaseRepository<Relation, Long> {

    Relation save(Relation relation);

    /**
     * 查询某个节点的第N级父节点。如果descendant指定的节点不存在、操作错误或是数据库被外部修改，
     * 则可能查询不到父节点，此时返回null。
     *
     * @param descendant 后代节点
     * @param distance   和父代节点的距离
     * @return
     */
    Optional<Long> queryAncestorByDescendantAndDistance(Long descendant, Integer distance);

    /**
     * 查询祖先和后代的距离
     *
     * @param ancestor
     * @param descendant
     * @return
     */
    Optional<Integer> queryDistanceByAncestorAndDescendant(Long ancestor, Long descendant);

    /**
     * 复制父节点的路径结构,并修改descendant和distance
     *
     * @param id
     * @param parent
     */
    void insertPath(Long id, Long parent);

    /**
     * 从树中删除某节点的路径。注意指定的节点可能存在子树，而子树的节点在该节点之上的路径并没有改变，
     * 所以使用该方法后还必须手动修改子节点的路径以确保树的正确性
     *
     * @param descendant 删除某节点
     */
    void deleteByDescendant(Long descendant);

    /**
     * 查找某节点下的所有直属子节点的 ID。
     *
     * @return
     */
    List<Long> queryDescendantByAncestorAndDistanceIsOne(Long ancestor);

    /**
     * 删除所有
     */
    void deleteAll();

    /**
     * 统计某个节点，距离为 distance 的数量
     *
     * @param ancestor
     * @param distance
     * @return
     */
    Long countByAncestorAndDistance(Long ancestor, Integer distance);

    /**
     * @param ancestor
     * @param distance
     * @return
     */
    List<Long> findDescendantByAncestorAndDistance(Long ancestor, Integer distance);


    /**
     * 查询某个的上级，并按照level 从近到远排序
     *
     * @param descendant
     * @return
     */
    List<Long> findAllAncestorIdAndOrderByDistance(Long descendant, Integer goeDistance, Integer limit);


    Page<Relation> page(RelationQueryRequest query);


}

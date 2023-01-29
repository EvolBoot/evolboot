package org.evolboot.identity.domain.user.relation;

import org.evolboot.core.data.Page;

import java.util.List;

/**
 * 用户上下级关系
 *
 * @author evol
 * 
 */
public interface RelationAppService {


    Page<Relation> page(RelationQuery query);

    void create(Long ancestor, Long descendant);

    void move(Long id, Long target);

    void moveTree(Long id, Long target);

    void deleteAll();

    Long countByAncestorAndDistance(Long ancestor, Integer distance);

    List<Long> findDescendantByAncestorAndDistance(Long ancestor, Integer distance);

    /**
     * 查找上级并排序
     *
     * @param descendant  下级ID
     * @param goeDistance 距离，也就是Level, 为0时 也包含自己，为1时，查所有上级
     * @param limit       查询几个上级
     * @return
     */
    List<Long> findAllAncestorIdAndOrderByDistance(Long descendant, Integer goeDistance, Integer limit);

}


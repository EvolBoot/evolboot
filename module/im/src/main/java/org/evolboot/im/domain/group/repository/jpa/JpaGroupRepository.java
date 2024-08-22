package org.evolboot.im.domain.group.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.dto.GroupQueryRequest;
import org.evolboot.im.domain.group.entity.QGroup;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Repository
public interface JpaGroupRepository extends GroupRepository, ExtendedQuerydslPredicateExecutor<Group, Long>, JpaRepository<Group, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        GroupQueryRequest query = (GroupQueryRequest) _query;
        QGroup q = QGroup.group;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.createAt.desc());
        jpqlQuery.select(select).from(q);
        if (ExtendObjects.nonNull(query.getId())) {
            jpqlQuery.where(q.id.eq(query.getId()));
        }
        if (ExtendObjects.nonNull(query.getBeginAt())) {
            jpqlQuery.where(q.createAt.goe(query.getBeginAt()));
        }
        if (ExtendObjects.nonNull(query.getEndAt())) {
            jpqlQuery.where(q.createAt.loe(query.getEndAt()));
        }
        return jpqlQuery;
    }

    @Override
    default List<Group> findAll() {
        QGroup q = QGroup.group;
        JPQLQuery<Group> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }

    @Override
    default <Q extends Query> Page<Group> page(Q query) {
        JPQLQuery<Group> jpqlQuery = fillQueryParameter(query, QGroup.group);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<Group> findOne(Q query) {
        return findOne(fillQueryParameter(query, QGroup.group));
    }

    @Override
    default <Q extends Query> List<Group> findAll(Q query) {
        return findAll(fillQueryParameter(query, QGroup.group));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QGroup.group.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}

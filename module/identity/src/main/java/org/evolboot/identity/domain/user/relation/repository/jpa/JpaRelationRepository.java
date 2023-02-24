package org.evolboot.identity.domain.user.relation.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.user.relation.QRelation;
import org.evolboot.identity.domain.user.relation.Relation;
import org.evolboot.identity.domain.user.relation.RelationId;
import org.evolboot.identity.domain.user.relation.RelationQuery;
import org.evolboot.identity.domain.user.relation.repository.RelationRepository;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 用户上下级关系
 *
 * @author evol
 * 
 */
@Repository
public interface JpaRelationRepository extends RelationRepository, ExtendedQuerydslPredicateExecutor<Relation>, JpaRepository<Relation, RelationId> {

    default JPQLQuery<Relation> fillQueryParameter(RelationQuery query) {
        QRelation q = QRelation.relation;
        JPQLQuery<Relation> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        if (ExtendObjects.nonNull(query.getAncestor())) {
            jpqlQuery.where(q.ancestor.eq(query.getAncestor()));
        }
        if (ExtendObjects.nonNull(query.getDescendant())) {
            jpqlQuery.where(q.descendant.eq(query.getDescendant()));
        }
        if (ExtendObjects.nonNull(query.getDistance())) {
            if (ExtendObjects.nonNull(query.getDistanceOperator())) {
                switch (query.getDistanceOperator()) {
                    case GOE: {
                        jpqlQuery.where(q.distance.goe(query.getDistance()));
                        break;
                    }
                    case LT: {
                        jpqlQuery.where(q.distance.lt(query.getDistance()));
                        break;
                    }
                    case GT: {
                        jpqlQuery.where(q.distance.gt(query.getDistance()));
                        break;
                    }
                }
            } else {
                jpqlQuery.where(q.distance.eq(query.getDistance()));
            }
        }
        return jpqlQuery;
    }

    @Override
    default List<Relation> findAll() {
        QRelation q = QRelation.relation;
        JPQLQuery<Relation> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    @Modifying
    @Query(value = "insert into evoltb_identity_user_relation (ancestor_,descendant_,distance_) (select ancestor_,:id,distance_ + 1 FROM evoltb_identity_user_relation WHERE descendant_ = :parent)", nativeQuery = true)
    void insertPath(@Param("id") Long id, @Param("parent") Long parent);

    @Override
    @Modifying
    @Query(value = "delete from evoltb_identity_user_relation WHERE descendant_ = :descendant", nativeQuery = true)
    void deleteByDescendant(@Param("descendant") Long descendant);

    @Override
    default Optional<Long> queryAncestorByDescendantAndDistance(Long descendant, Integer distance) {
        QRelation q = QRelation.relation;
        JPQLQuery<Long> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q.ancestor).from(q).where(q.descendant.eq(descendant), q.distance.eq(distance));
        return findOne(jpqlQuery);
    }

    @Override
    default List<Long> queryDescendantByAncestorAndDistanceIsOne(Long ancestor) {
        QRelation q = QRelation.relation;
        JPQLQuery<Long> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q.descendant).from(q).where(q.ancestor.eq(ancestor), q.distance.eq(1));
        return findAll(jpqlQuery);
    }

    @Override
    @Modifying
    @Query(value = "delete from evoltb_identity_user_relation where descendant_ <> 0 ", nativeQuery = true)
    void deleteAll();

    @Override
    default Long countByAncestorAndDistance(Long ancestor, Integer distance) {
        QRelation q = QRelation.relation;
        JPQLQuery<Long> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q.count()).from(q).where(q.ancestor.eq(ancestor), q.distance.eq(distance));
        return findOne(jpqlQuery).orElse(0L);
    }

    @Override
    default List<Long> findDescendantByAncestorAndDistance(Long ancestor, Integer distance) {
        QRelation q = QRelation.relation;
        JPQLQuery<Long> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q.descendant).from(q).where(q.ancestor.eq(ancestor), q.distance.eq(distance));
        return findAll(jpqlQuery);
    }

    @Override
    default List<Long> findAllAncestorIdAndOrderByDistance(Long descendant, Integer goeDistance, Integer limit) {
        QRelation q = QRelation.relation;
        JPQLQuery<Long> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q.ancestor).from(q).where(q.descendant.eq(descendant), q.distance.goe(goeDistance)).orderBy(q.distance.asc());
        if (ExtendObjects.nonNull(limit) && limit > 0) {
            jpqlQuery.limit(limit);
        }
        return findAll(jpqlQuery);
    }

    @Override
    default Page<Relation> page(RelationQuery query) {
        JPQLQuery<Relation> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}

package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.jpa;

import projectPackage.core.data.Page;
import projectPackage.core.data.Query;
import projectPackage.core.data.PageImpl;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.QXarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncQuery;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.XarvkgvvrllncRepository;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import projectPackage.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import com.querydsl.jpa.JPQLQuery;
import projectPackage.core.util.ExtendObjects;

import java.util.List;
import java.util.Optional;
import java.util.Date;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Repository
public interface JpaXarvkgvvrllncRepository extends XarvkgvvrllncRepository, ExtendedQuerydslPredicateExecutor<Xarvkgvvrllnc, Keya2Akk5iV3n>, JpaRepository<Xarvkgvvrllnc, Keya2Akk5iV3n> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        XarvkgvvrllncQuery query = (XarvkgvvrllncQuery) _query;
        QXarvkgvvrllnc q = QXarvkgvvrllnc.instantiationObjectName;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q).orderBy(q.createAt.desc());
        if (ExtendObjects.nonNull(query.getId())) {
            jpqlQuery.where(q.id.eq(query.getId()));
        }
        if (ExtendObjects.nonNull(query.getStartDate())) {
            jpqlQuery.where(q.createAt.goe(query.getStartDate()));
        }
        if (ExtendObjects.nonNull(query.getEndDate())) {
            jpqlQuery.where(q.createAt.loe(query.getEndDate()));
        }
        return jpqlQuery;
    }

    @Override
    default List<Xarvkgvvrllnc> findAll() {
        QXarvkgvvrllnc q = QXarvkgvvrllnc.instantiationObjectName;
        JPQLQuery<Xarvkgvvrllnc> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<Xarvkgvvrllnc> page(Q query) {
        JPQLQuery<Xarvkgvvrllnc> jpqlQuery = fillQueryParameter(query, QXarvkgvvrllnc.instantiationObjectName);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<Xarvkgvvrllnc> findOne(Q query) {
        return findOne(fillQueryParameter(query, QXarvkgvvrllnc.instantiationObjectName));
    }

    @Override
    default <Q extends Query> List<Xarvkgvvrllnc> findAll(Q query) {
        return findAll(fillQueryParameter(query, QXarvkgvvrllnc.instantiationObjectName));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QXarvkgvvrllnc.instantiationObjectName.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}

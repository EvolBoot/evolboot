package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.jpa;

import projectPackage.core.data.Page;
import projectPackage.core.data.PageImpl;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.QXarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.XarvkgvvrllncQuery;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.XarvkgvvrllncRepository;
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
public interface JpaXarvkgvvrllncRepository extends XarvkgvvrllncRepository, ExtendedQuerydslPredicateExecutor<Xarvkgvvrllnc>, JpaRepository<Xarvkgvvrllnc, Keya2Akk5iV3n> {

    default JPQLQuery<Xarvkgvvrllnc> fillQueryParameter(XarvkgvvrllncQuery query) {
        QXarvkgvvrllnc q = QXarvkgvvrllnc.instantiationObjectName;
        JPQLQuery<Xarvkgvvrllnc> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
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
    default List<Xarvkgvvrllnc> findAll(XarvkgvvrllncQuery query) {
        JPQLQuery<Xarvkgvvrllnc> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<Xarvkgvvrllnc> page(XarvkgvvrllncQuery query) {
        JPQLQuery<Xarvkgvvrllnc> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default Optional<Xarvkgvvrllnc> findOne(XarvkgvvrllncQuery query) {
        return findOne(fillQueryParameter(query));
    }
}

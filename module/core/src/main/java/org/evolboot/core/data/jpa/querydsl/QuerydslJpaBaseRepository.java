package org.evolboot.core.data.jpa.querydsl;

import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.AbstractJPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendObjects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.NonNull;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * QueryDSL 实现，但同时也兼容了JPA实现
 *
 * @param <T>
 * @param <ID>
 */
public class QuerydslJpaBaseRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExtendedQuerydslPredicateExecutor<T, ID> {


    private final QuerydslPredicateExecutor<T> querydslPredicateExecutor;
    private final Querydsl querydsl;

    private JPAQueryFactory queryFactory;


    public QuerydslJpaBaseRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        this(entityInformation, entityManager, SimpleEntityPathResolver.INSTANCE);
    }

    public QuerydslJpaBaseRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, EntityPathResolver resolver) {
        super(entityInformation, entityManager);
        this.querydslPredicateExecutor = new QuerydslPredicateExecutor<>(entityInformation, entityManager, SimpleEntityPathResolver.INSTANCE, null);
        EntityPath<T> path = resolver.createPath(entityInformation.getJavaType());
        PathBuilder<T> builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(entityManager, builder);
        this.queryFactory = new JPAQueryFactory(entityManager);

    }

    @Override
    @NonNull
    public <P> Optional<P> findOne(@NonNull JPQLQuery<P> query) {
        return Optional.ofNullable(query.fetchFirst());
    }

    @Override
    @NonNull
    public <P> Optional<P> findOne(@NonNull FactoryExpression<P> factoryExpression, @NonNull Predicate predicate) {
        JPQLQuery<P> query = createQuery(factoryExpression, predicate);
        return findOne(query);
    }

    @Override
    @NonNull
    public <P> List<P> findAll(@NonNull JPQLQuery<P> query) {
        return query.fetch();
    }

    @Override
    @NonNull
    public <P> Page<P> findAll(@NonNull JPQLQuery<P> query, @NonNull Pageable pageable) {
        return getPage(query, query, pageable);
    }

    @Override
    @NonNull
    public <P> List<P> findAll(@NonNull FactoryExpression<P> factoryExpression, @NonNull Predicate predicate) {
        JPQLQuery<P> query = createQuery(factoryExpression, predicate);
        return findAll(query);
    }

    @Override
    @NonNull
    public <P> Page<P> findAll(@NonNull FactoryExpression<P> factoryExpression, @NonNull Predicate predicate, @NonNull Pageable pageable) {
        JPQLQuery<P> query = createQuery(factoryExpression, predicate);
        JPQLQuery<?> countQuery = querydslPredicateExecutor.createCountQuery(predicate);
        return getPage(query, countQuery, pageable);
    }

    @Override
    public <P> JPQLQuery<P> getJPQLQuery() {
        return querydsl.createQuery();
    }

    @Override
    public <P, Q extends Query> JPQLQuery<P> getJPQLQuery(Q q, OrderSpecifier<?> defaultOrder) {
        JPQLQuery<P> jpqlQuery = getJPQLQuery();
        orderBy(q, defaultOrder, jpqlQuery);
        return jpqlQuery;
    }

    @Override
    public <P, Q extends Query> JPQLQuery<P> getJPQLQuery(Q q) {
        return getJPQLQuery(q, null);
    }

    @Override
    public <P, Q extends Query> void orderBy(Q q, OrderSpecifier<?> defaultOrder, JPQLQuery<P> jpqlQuery) {
        if (ExtendObjects.isNotBlank(q.getSortField()) && ExtendObjects.nonNull(q.getDirection())) {
            Path<Object> path = Expressions.path(Object.class, q.getSortField());
            jpqlQuery.orderBy(new OrderSpecifier(Order.valueOf(q.getDirection().name()), path));
        } else if (ExtendObjects.nonNull(defaultOrder)) {
            jpqlQuery.orderBy(defaultOrder);
        }
    }

    @Override
    public JPAQueryFactory getQueryFactory() {
        return queryFactory;
    }

    @Override
    @NonNull
    public Optional<T> findOne(@NonNull Predicate predicate) {
        return querydslPredicateExecutor.findOne(predicate);
    }

    @Override
    @NonNull
    public Iterable<T> findAll(@NonNull Predicate predicate) {
        return querydslPredicateExecutor.findAll(predicate);
    }

    @Override
    @NonNull
    public Iterable<T> findAll(@NonNull Predicate predicate, @NonNull Sort sort) {
        return querydslPredicateExecutor.findAll(predicate, sort);
    }


    @Override
    @NonNull
    public Iterable<T> findAll(@NonNull Predicate predicate, @NonNull OrderSpecifier<?>... orders) {
        return querydslPredicateExecutor.findAll(predicate, orders);
    }

    @Override
    @NonNull
    public Iterable<T> findAll(@NonNull OrderSpecifier<?>... orders) {
        return querydslPredicateExecutor.findAll(orders);
    }

    @Override
    @NonNull
    public Page<T> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable) {
        return querydslPredicateExecutor.findAll(predicate, pageable);
    }

    @Override
    public long count(@NonNull Predicate predicate) {
        return querydslPredicateExecutor.count(predicate);
    }

    @Override
    public boolean exists(@NonNull Predicate predicate) {
        return querydslPredicateExecutor.exists(predicate);
    }

    @Override
    public <S extends T, R> R findBy(Predicate predicate, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return querydslPredicateExecutor.findBy(predicate, queryFunction);
    }

    private <P> JPQLQuery<P> createQuery(FactoryExpression<P> factoryExpression, Predicate predicate) {
        return querydslPredicateExecutor
                .createQuery(predicate)
                .select(factoryExpression);
    }

    private <P> Page<P> getPage(JPQLQuery<P> query, JPQLQuery<?> countQuery, Pageable pageable) {
        JPQLQuery<P> paginatedQuery = querydsl.applyPagination(pageable, query);
        return PageableExecutionUtils.getPage(paginatedQuery.fetch(), pageable, countQuery::fetchCount);
    }

    private static class QuerydslPredicateExecutor<T> extends QuerydslJpaPredicateExecutor<T> {
        QuerydslPredicateExecutor(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, EntityPathResolver resolver, CrudMethodMetadata metadata) {
            super(entityInformation, entityManager, resolver, metadata);
        }

        @Override
        @NonNull
        public JPQLQuery<?> createCountQuery(Predicate... predicate) {
            return super.createCountQuery(predicate);
        }

        @Override
        @NonNull
        public AbstractJPAQuery<?, ?> createQuery(Predicate... predicate) {
            return super.createQuery(predicate);
        }
    }
}

package org.evolboot.core.data.jpa.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * QueryDSL 辅助器
 *
 * @author evol
 */
public class QuerydslJpaRepository {

    private final JPAQueryFactory queryFactory;

    public QuerydslJpaRepository(EntityManager entityManager) {
        queryFactory = new JPAQueryFactory(entityManager);
    }


    /**
     * 查询全部
     * @param query
     * @return
     * @param <P>
     */
    public <P> List<P> findAll(@NonNull JPQLQuery<P> query) {
        return query.fetch();
    }


    /**
     * 分页
     * @param query
     * @param pageable
     * @return
     * @param <P>
     */
    public <P> Page<P> findAll(@NonNull JPQLQuery<P> query, @NonNull Pageable pageable) {
        return getPage(query, query, pageable);
    }

    private <P> Page<P> getPage(JPQLQuery<P> query, JPQLQuery<?> countQuery, Pageable pageable) {
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);
    }


    public JPQLQuery<?> getJPQLQuery() {
        return queryFactory.query();
    }
}

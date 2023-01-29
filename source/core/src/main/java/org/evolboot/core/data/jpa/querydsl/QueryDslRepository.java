package org.evolboot.core.data.jpa.querydsl;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public abstract class QueryDslRepository<T> {

    protected final JPAQueryFactory queryFactory;

    public QueryDslRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Page<T> convert(QueryResults<T> queryResults, int page) {
        return PageImpl.<T>builder()
                .list(queryResults.getResults())
                .totalSize(queryResults.getTotal())
                .page(page)
                .limit((int) queryResults.getLimit())
                .build();
    }


    protected abstract JPAQuery<T> getQuery();


}

package org.evolboot.system.domain.news.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.system.domain.news.News;
import org.evolboot.system.domain.news.NewsQuery;
import org.evolboot.system.domain.news.QNews;
import org.evolboot.system.domain.news.repository.NewsRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 新闻
 *
 * @author evol
 */
@Repository
public interface JpaNewsRepository extends NewsRepository, ExtendedQuerydslPredicateExecutor<News, Long>, JpaRepository<News, Long> {

    String CACHE_KEY = RedisCacheName.NEWS_CACHE_KEY;

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        NewsQuery query = (NewsQuery) _query;
        QNews q = QNews.news;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q).orderBy(q.sort.desc());
        if (ExtendObjects.nonNull(query.getShow())) {
            jpqlQuery.where(q.show.eq(query.getShow()));
        }
        return jpqlQuery;
    }


    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    News save(News news);

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteById(Long id);

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'all'")
    default List<News> findAll() {
        QNews q = QNews.news;
        JPQLQuery<News> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<News> page(Q query) {
        JPQLQuery<News> jpqlQuery = fillQueryParameter(query, QNews.news);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<News> findOne(Q query) {
        return findOne(fillQueryParameter(query, QNews.news));
    }

    @Override
    default <Q extends Query> List<News> findAll(Q query) {
        return findAll(fillQueryParameter(query, QNews.news));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QNews.news.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}

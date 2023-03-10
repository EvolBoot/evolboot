package org.evolboot.content.domain.news.repository.jpa;

import org.evolboot.content.domain.news.News;
import org.evolboot.content.domain.news.NewsQuery;
import org.evolboot.content.domain.news.QNews;
import org.evolboot.content.domain.news.repository.NewsRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.cache.RedisCacheName;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 新闻
 *
 * @author evol
 * 
 */
@Repository
public interface JpaNewsRepository extends NewsRepository, ExtendedQuerydslPredicateExecutor<News>, JpaRepository<News, Long> {

    String CACHE_KEY = RedisCacheName.NEWS_CACHE_KEY;

    default JPQLQuery<News> fillQueryParameter(NewsQuery query) {
        QNews q = QNews.news;
        JPQLQuery<News> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.sort.desc());
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
    default List<News> findAll(NewsQuery query) {
        JPQLQuery<News> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<News> page(NewsQuery query) {
        JPQLQuery<News> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}

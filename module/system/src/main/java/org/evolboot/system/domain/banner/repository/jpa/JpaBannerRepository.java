package org.evolboot.system.domain.banner.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.system.domain.banner.Banner;
import org.evolboot.system.domain.banner.BannerQuery;
import org.evolboot.system.domain.banner.QBanner;
import org.evolboot.system.domain.banner.repository.BannerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * banner
 *
 * @author evol
 */
@Repository
public interface JpaBannerRepository extends BannerRepository, ExtendedQuerydslPredicateExecutor<Banner, Long>, JpaRepository<Banner, Long> {

    String CACHE_KEY = RedisCacheName.BANNER_CACHE_KEY;

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        BannerQuery query = (BannerQuery) _query;
        QBanner q = QBanner.banner;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q).orderBy(q.sort.desc());
        if (ExtendObjects.nonNull(query.getShow())) {
            jpqlQuery.where(q.show.eq(query.getShow()));
        }
        return jpqlQuery;
    }

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    Banner save(Banner banner);


    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteById(Long id);


    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'all'")
    default List<Banner> findAll() {
        QBanner q = QBanner.banner;
        JPQLQuery<Banner> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<Banner> page(Q query) {
        JPQLQuery<Banner> jpqlQuery = fillQueryParameter(query, QBanner.banner);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<Banner> findOne(Q query) {
        return findOne(fillQueryParameter(query, QBanner.banner));
    }

    @Override
    default <Q extends Query> List<Banner> findAll(Q query) {
        return findAll(fillQueryParameter(query, QBanner.banner));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QBanner.banner.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}

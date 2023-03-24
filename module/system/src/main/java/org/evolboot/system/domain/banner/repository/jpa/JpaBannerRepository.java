package org.evolboot.system.domain.banner.repository.jpa;

import com.querydsl.jpa.JPQLQuery;
import org.evolboot.system.domain.banner.Banner;
import org.evolboot.system.domain.banner.BannerQuery;
import org.evolboot.system.domain.banner.QBanner;
import org.evolboot.system.domain.banner.repository.BannerRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.cache.RedisCacheName;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * banner
 *
 * @author evol
 * 
 */
@Repository
public interface JpaBannerRepository extends BannerRepository, ExtendedQuerydslPredicateExecutor<Banner>, JpaRepository<Banner, Long> {

    String CACHE_KEY = RedisCacheName.BANNER_CACHE_KEY;

    default JPQLQuery<Banner> fillQueryParameter(BannerQuery query) {
        QBanner q = QBanner.banner;
        JPQLQuery<Banner> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.sort.desc());
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
    default List<Banner> findAll(BannerQuery query) {
        JPQLQuery<Banner> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<Banner> page(BannerQuery query) {
        JPQLQuery<Banner> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}

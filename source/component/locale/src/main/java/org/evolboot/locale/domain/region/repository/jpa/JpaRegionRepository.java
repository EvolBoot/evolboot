package org.evolboot.locale.domain.region.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.locale.domain.region.QRegion;
import org.evolboot.locale.domain.region.Region;
import org.evolboot.locale.domain.region.RegionQuery;
import org.evolboot.locale.domain.region.repository.RegionRepository;
import org.evolboot.shared.cache.RedisCacheName;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author evol
 */
@Repository
public interface JpaRegionRepository extends RegionRepository, ExtendedQuerydslPredicateExecutor<Region>, JpaRepository<Region, Long> {

    String CACHE_KEY = RedisCacheName.LOCALE_COUNTRY_CACHE_KEY;

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    Region save(Region region);


    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteById(Long id);

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'enableIsTrue'")
    default List<Region> findAllByEnableIsTrue() {
        QRegion q = QRegion.region;
        JPQLQuery<Region> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q)
                .where(q.enable.eq(true))
                .orderBy(q.sort.desc());
        return findAll(jpqlQuery);
    }

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'all'")
    default List<Region> findAll() {
        QRegion q = QRegion.region;
        JPQLQuery<Region> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<Region> page(RegionQuery query) {
        QRegion q = QRegion.region;
        JPQLQuery<Region> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        jpqlQuery.orderBy(q.sort.desc());
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}

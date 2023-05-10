package org.evolboot.system.domain.notice.repository.jpa;

import com.querydsl.jpa.JPQLQuery;
import org.evolboot.system.domain.notice.Notice;
import org.evolboot.system.domain.notice.NoticeQuery;
import org.evolboot.system.domain.notice.QNotice;
import org.evolboot.system.domain.notice.repository.NoticeRepository;
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
import java.util.Optional;

/**
 * 公告
 *
 * @author evol
 * 
 */
@Repository
public interface JpaNoticeRepository extends NoticeRepository, ExtendedQuerydslPredicateExecutor<Notice>, JpaRepository<Notice, Long> {

    String CACHE_KEY = RedisCacheName.NOTICE_CACHE_KEY;

    default JPQLQuery<Notice> fillQueryParameter(NoticeQuery query) {
        QNotice q = QNotice.notice;
        JPQLQuery<Notice> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.sort.desc());
        if (ExtendObjects.nonNull(query.getEnable())) {
            jpqlQuery.where(q.enable.eq(query.getEnable()));
        }
        return jpqlQuery;
    }

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    Notice save(Notice notice);

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteById(Long id);

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'all'")
    default List<Notice> findAll() {
        QNotice q = QNotice.notice;
        JPQLQuery<Notice> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default List<Notice> findAll(NoticeQuery query) {
        JPQLQuery<Notice> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<Notice> page(NoticeQuery query) {
        JPQLQuery<Notice> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'lastest'")
    default Optional<Notice> findByLatest() {
        QNotice q = QNotice.notice;
        JPQLQuery<Notice> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).where(q.enable.eq(true)).orderBy(q.sort.desc());
        return findOne(jpqlQuery);
    }


    @Override
    default Optional<Notice> findOne(NoticeQuery query) {
        return findOne(fillQueryParameter(query));
    }
}

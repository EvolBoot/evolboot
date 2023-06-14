package org.evolboot.system.domain.notice.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.system.domain.notice.entity.Notice;
import org.evolboot.system.domain.notice.service.NoticeQuery;
import org.evolboot.system.domain.notice.entity.QNotice;
import org.evolboot.system.domain.notice.repository.NoticeRepository;
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
 */
@Repository
public interface JpaNoticeRepository extends NoticeRepository, ExtendedQuerydslPredicateExecutor<Notice, Long>, JpaRepository<Notice, Long> {

    String CACHE_KEY = RedisCacheName.NOTICE_CACHE_KEY;

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        NoticeQuery query = (NoticeQuery) _query;
        QNotice q = QNotice.notice;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.sort.desc());
        jpqlQuery.select(select).from(q);
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
    @Cacheable(cacheNames = CACHE_KEY, key = "'lastest'")
    default Optional<Notice> findByLatest() {
        QNotice q = QNotice.notice;
        JPQLQuery<Notice> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).where(q.enable.eq(true)).orderBy(q.sort.desc());
        return findOne(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<Notice> page(Q query) {
        JPQLQuery<Notice> jpqlQuery = fillQueryParameter(query, QNotice.notice);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<Notice> findOne(Q query) {
        return findOne(fillQueryParameter(query, QNotice.notice));
    }

    @Override
    default <Q extends Query> List<Notice> findAll(Q query) {
        return findAll(fillQueryParameter(query, QNotice.notice));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QNotice.notice.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}

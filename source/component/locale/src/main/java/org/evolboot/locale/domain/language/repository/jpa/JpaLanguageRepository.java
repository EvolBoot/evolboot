package org.evolboot.locale.domain.language.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.locale.domain.language.Language;
import org.evolboot.locale.domain.language.LanguageQuery;
import org.evolboot.locale.domain.language.QLanguage;
import org.evolboot.locale.domain.language.repository.LanguageRepository;
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
public interface JpaLanguageRepository extends LanguageRepository, ExtendedQuerydslPredicateExecutor<Language>, JpaRepository<Language, Long> {

    String CACHE_KEY = RedisCacheName.LOCALE_LANGUAGE_CACHE_KEY;


    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    Language save(Language language);

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteById(Long id);


    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'enableisTsTrue'")
    List<Language> findAllByEnableIsTrueOrderBySortAsc();


    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'all'")
    default List<Language> findAll() {
        QLanguage q = QLanguage.language1;
        JPQLQuery<Language> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default Page<Language> page(LanguageQuery query) {
        QLanguage q = QLanguage.language1;
        JPQLQuery<Language> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        jpqlQuery.orderBy(q.sort.desc());
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}

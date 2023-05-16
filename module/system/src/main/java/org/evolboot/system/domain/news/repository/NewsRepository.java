package org.evolboot.system.domain.news.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.system.domain.news.News;

import java.util.List;
import java.util.Optional;

/**
 * 新闻
 *
 * @author evol
 */
public interface NewsRepository extends BaseRepository<News, Long> {

    News save(News news);

    Optional<News> findById(Long id);


    void deleteById(Long id);

    List<News> findAll();


    <Q extends Query> List<News> findAll(Q query);

    <Q extends Query> Optional<News> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<News> page(Q query);
}

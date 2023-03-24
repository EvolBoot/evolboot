package org.evolboot.system.domain.news.repository;

import org.evolboot.system.domain.news.News;
import org.evolboot.system.domain.news.NewsQuery;
import org.evolboot.core.data.Page;

import java.util.List;
import java.util.Optional;

/**
 * 新闻
 *
 * @author evol
 * 
 */
public interface NewsRepository {

    News save(News news);

    Optional<News> findById(Long id);

    Page<News> page(NewsQuery query);

    void deleteById(Long id);

    List<News> findAll();

    List<News> findAll(NewsQuery query);
}

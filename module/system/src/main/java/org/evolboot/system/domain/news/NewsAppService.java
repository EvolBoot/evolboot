package org.evolboot.system.domain.news;

import org.evolboot.system.domain.news.service.NewsCreateFactory;
import org.evolboot.system.domain.news.service.NewsUpdateService;
import org.evolboot.core.data.Page;

import java.util.List;

/**
 * 新闻
 *
 * @author evol
 * 
 */
public interface NewsAppService {

    News create(NewsCreateFactory.Request request);

    void update(Long id, NewsUpdateService.Request request);

    void delete(Long id);

    List<News> findAll();

    List<News> findAll(NewsQuery query);

    Page<News> page(NewsQuery query);

    News findById(Long id);


}

package org.evolboot.content.domain.news;

import org.evolboot.content.ContentI18nMessage;
import org.evolboot.content.domain.news.repository.NewsRepository;
import org.evolboot.core.exception.DomainNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * 新闻
 *
 * @author evol
 */
@Slf4j
public abstract class NewsSupportService {

    final NewsRepository repository;

    protected NewsSupportService(NewsRepository repository) {
        this.repository = repository;
    }

    public News findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(ContentI18nMessage.News.notFound()));
    }

}

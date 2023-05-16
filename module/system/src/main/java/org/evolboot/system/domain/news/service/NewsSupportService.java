package org.evolboot.system.domain.news.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.news.News;
import org.evolboot.system.domain.news.repository.NewsRepository;

/**
 * 新闻
 *
 * @author evol
 */
@Slf4j
public abstract class NewsSupportService {

    protected final NewsRepository repository;

    protected NewsSupportService(NewsRepository repository) {
        this.repository = repository;
    }

    public News findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(SystemI18nMessage.News.notFound()));
    }


}

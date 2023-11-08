package org.evolboot.system.domain.news.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.news.repository.NewsRepository;
import org.evolboot.system.domain.news.service.NewsSupportService;
import org.springframework.stereotype.Service;

/**
 * 新闻
 *
 * @author evol
 */
@Service
@Slf4j
public class NewsListener {

    private final NewsRepository repository;

    private final NewsSupportService supportService;

    protected NewsListener(NewsRepository repository, NewsSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

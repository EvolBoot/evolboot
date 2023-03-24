package org.evolboot.system.domain.news;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.news.repository.NewsRepository;
import org.evolboot.system.domain.news.service.NewsSupportService;
import org.springframework.stereotype.Service;

/**
 * 新闻
 *
 * @author evol
 * 
 */
@Service
@Slf4j
public class NewsListener extends NewsSupportService {

    protected NewsListener(NewsRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

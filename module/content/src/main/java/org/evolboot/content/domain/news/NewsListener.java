package org.evolboot.content.domain.news;

import org.evolboot.content.domain.news.repository.NewsRepository;
import lombok.extern.slf4j.Slf4j;
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

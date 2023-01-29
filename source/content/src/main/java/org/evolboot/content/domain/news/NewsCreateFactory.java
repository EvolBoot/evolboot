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
@Slf4j
@Service
public class NewsCreateFactory extends NewsSupportService {

    protected NewsCreateFactory(NewsRepository repository) {
        super(repository);
    }

    public News execute(Request request) {
        News news = new News(
                request.getSort(),
                request.getSource(),
                request.getReleasedTime(),
                request.getShow(),
                request.getLocales()
        );
        repository.save(news);
        return news;
    }

    public static class Request extends NewsRequestBase {

    }

}

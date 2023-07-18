package org.evolboot.system.domain.news.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.news.entity.News;
import org.evolboot.system.domain.news.repository.NewsRepository;
import org.springframework.stereotype.Service;

/**
 * 新闻
 *
 * @author evol
 */
@Slf4j
@Service
public class NewsUpdateService extends NewsSupportService {
    protected NewsUpdateService(NewsRepository repository) {
        super(repository);
    }

    public void execute(Request request) {
        News news = findById(request.getId());
        news.setSort(request.getSort());
        news.setSource(request.getSource());
        news.setShow(request.getShow());
        news.setLocales(request.getLocales());
        news.setReleasedTime(request.getReleasedTime());
        repository.save(news);
    }

    @Getter
    @Setter
    public static class Request extends NewsRequestBase {
        private Long id;
    }

}

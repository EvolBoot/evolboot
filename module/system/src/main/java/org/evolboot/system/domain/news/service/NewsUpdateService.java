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
public class NewsUpdateService {

    private final NewsRepository repository;
    private final NewsSupportService supportService;

    protected NewsUpdateService(NewsRepository repository, NewsSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        News news = supportService.findById(request.getId());
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

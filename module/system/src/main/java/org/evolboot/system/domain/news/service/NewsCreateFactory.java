package org.evolboot.system.domain.news.service;

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
public class  NewsCreateFactory {

    private final  NewsRepository repository;
    private final  NewsSupportService supportService;

    protected  NewsCreateFactory( NewsRepository repository,  NewsSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
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

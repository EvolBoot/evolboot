package org.evolboot.system.domain.news;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.system.domain.news.entity.News;
import org.evolboot.system.domain.news.repository.NewsRepository;
import org.evolboot.system.domain.news.service.NewsCreateFactory;
import org.evolboot.system.domain.news.service.NewsQuery;
import org.evolboot.system.domain.news.service.NewsSupportService;
import org.evolboot.system.domain.news.service.NewsUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 新闻
 *
 * @author evol
 */
@Slf4j
@Service
public class NewsAppServiceImpl extends NewsSupportService implements NewsAppService {


    private final NewsCreateFactory factory;
    private final NewsUpdateService updateService;

    protected NewsAppServiceImpl(NewsRepository repository, NewsCreateFactory factory, NewsUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public News create(NewsCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, NewsUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<News> findAll() {
        return repository.findAll();
    }


    @Override
    public List<News> findAll(NewsQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<News> page(NewsQuery query) {
        return repository.page(query);
    }

    @Override
    public Optional<News> findOne(NewsQuery query) {
        return repository.findOne(query);
    }

}

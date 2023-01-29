package org.evolboot.content.domain.news;

import org.evolboot.content.domain.news.repository.NewsRepository;
import org.evolboot.core.data.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 新闻
 *
 * @author evol
 * 
 */
@Slf4j
@Service
public class DefaultNewsAppService extends NewsSupportService implements NewsAppService {


    private final NewsCreateFactory factory;
    private final NewsUpdateService updateService;

    protected DefaultNewsAppService(NewsRepository repository, NewsCreateFactory factory, NewsUpdateService updateService) {
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

}

package org.evolboot.system.domain.notice;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.system.domain.notice.repository.NoticeRepository;
import org.evolboot.system.domain.notice.service.NoticeCreateFactory;
import org.evolboot.system.domain.notice.service.NoticeSupportService;
import org.evolboot.system.domain.notice.service.NoticeUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公告
 *
 * @author evol
 */
@Slf4j
@Service
public class DefaultNoticeAppService extends NoticeSupportService implements NoticeAppService {


    private final NoticeCreateFactory factory;
    private final NoticeUpdateService updateService;

    protected DefaultNoticeAppService(NoticeRepository repository, NoticeCreateFactory factory, NoticeUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public Notice create(NoticeCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, NoticeUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<Notice> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Notice> findAll(NoticeQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Notice> page(NoticeQuery query) {
        return repository.page(query);
    }

    @Override
    public Notice findByLatest() {
        return repository.findByLatest().orElse(null);
    }

}

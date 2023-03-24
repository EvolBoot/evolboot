package org.evolboot.system.domain.notice.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.notice.Notice;
import org.evolboot.system.domain.notice.NoticeRequestBase;
import org.evolboot.system.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;

/**
 * 公告
 *
 * @author evol
 * 
 */
@Slf4j
@Service
public class NoticeUpdateService extends NoticeSupportService {
    protected NoticeUpdateService(NoticeRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        Notice notice = findById(id);
        notice.setSort(request.getSort());
        notice.setEnable(request.getEnable());
        notice.setReleasedTime(request.getReleasedTime());
        notice.setLocales(request.getLocales());
        repository.save(notice);
    }

    public static class Request extends NoticeRequestBase {
    }

}

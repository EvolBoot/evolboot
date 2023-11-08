package org.evolboot.system.domain.notice.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.notice.entity.Notice;
import org.evolboot.system.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;

/**
 * 公告
 *
 * @author evol
 */
@Slf4j
@Service
public class NoticeUpdateService {

    private final NoticeRepository repository;
    private final NoticeSupportService supportService;

    protected NoticeUpdateService(NoticeRepository repository, NoticeSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        Notice notice = supportService.findById(request.getId());
        notice.setSort(request.getSort());
        notice.setEnable(request.getEnable());
        notice.setReleasedTime(request.getReleasedTime());
        notice.setLocales(request.getLocales());
        repository.save(notice);
    }

    @Getter
    @Setter
    public static class Request extends NoticeRequestBase {
        private Long id;
    }

}

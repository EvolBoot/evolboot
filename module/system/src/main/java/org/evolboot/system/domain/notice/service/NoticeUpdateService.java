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
public class NoticeUpdateService extends NoticeSupportService {
    protected NoticeUpdateService(NoticeRepository repository) {
        super(repository);
    }

    public void execute(Request request) {
        Notice notice = findById(request.getId());
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

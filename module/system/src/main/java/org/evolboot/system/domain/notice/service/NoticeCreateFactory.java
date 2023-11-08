package org.evolboot.system.domain.notice.service;

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
public class  NoticeCreateFactory {

    private final  NoticeRepository repository;
    private final  NoticeSupportService supportService;

    protected  NoticeCreateFactory( NoticeRepository repository,  NoticeSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }


    public Notice execute(Request request) {
        Notice notice = new Notice(
                request.getSort(),
                request.getEnable(),
                request.getReleasedTime(),
                request.getLocales()
        );
        repository.save(notice);
        return notice;
    }

    public static class Request extends NoticeRequestBase {

    }

}

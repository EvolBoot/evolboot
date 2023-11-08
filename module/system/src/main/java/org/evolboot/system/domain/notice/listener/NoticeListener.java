package org.evolboot.system.domain.notice.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.notice.repository.NoticeRepository;
import org.evolboot.system.domain.notice.service.NoticeSupportService;
import org.springframework.stereotype.Service;

/**
 * 公告
 *
 * @author evol
 */
@Service
@Slf4j
public class NoticeListener {

    private final NoticeRepository repository;

    private final NoticeSupportService supportService;

    protected NoticeListener(NoticeRepository repository, NoticeSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

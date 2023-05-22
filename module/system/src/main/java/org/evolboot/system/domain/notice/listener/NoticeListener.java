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
public class NoticeListener extends NoticeSupportService {

    protected NoticeListener(NoticeRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

package org.evolboot.content.domain.notice;

import org.evolboot.content.domain.notice.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 公告
 *
 * @author evol
 * 
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

package org.evolboot.content.domain.notice;

import org.evolboot.content.ContentI18nMessage;
import org.evolboot.content.domain.notice.repository.NoticeRepository;
import org.evolboot.core.exception.DomainNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * 公告
 *
 * @author evol
 * 
 */
@Slf4j
public abstract class NoticeSupportService {

    final NoticeRepository repository;

    protected NoticeSupportService(NoticeRepository repository) {
        this.repository = repository;
    }

    public Notice findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(ContentI18nMessage.Notice.notFound()));
    }

}

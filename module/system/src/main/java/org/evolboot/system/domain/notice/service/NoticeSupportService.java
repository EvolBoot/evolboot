package org.evolboot.system.domain.notice.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.notice.Notice;
import org.evolboot.system.domain.notice.repository.NoticeRepository;
import org.evolboot.core.exception.DomainNotFoundException;

/**
 * 公告
 *
 * @author evol
 * 
 */
@Slf4j
public abstract class NoticeSupportService {

    protected final NoticeRepository repository;

    protected NoticeSupportService(NoticeRepository repository) {
        this.repository = repository;
    }

    public Notice findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(SystemI18nMessage.Notice.notFound()));
    }

}

package org.evolboot.system.domain.notice.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.system.SystemI18nMessage;
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
public class NoticeSupportService {

    protected final NoticeRepository repository;

    protected NoticeSupportService(NoticeRepository repository) {
        this.repository = repository;
    }

    public Notice findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(SystemI18nMessage.Notice.notFound()));
    }

}

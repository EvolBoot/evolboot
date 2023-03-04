package org.evolboot.content.domain.startuppage.service;

import org.evolboot.content.ContentI18nMessage;
import org.evolboot.content.domain.startuppage.StartupPage;
import org.evolboot.content.domain.startuppage.repository.StartupPageRepository;
import org.evolboot.core.exception.DomainNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * 启动页
 *
 * @author evol
 * 
 */
@Slf4j
public abstract class StartupPageSupportService {

    protected final StartupPageRepository repository;

    protected StartupPageSupportService(StartupPageRepository repository) {
        this.repository = repository;
    }

    public StartupPage findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(ContentI18nMessage.StartupPage.notFound()));
    }

}

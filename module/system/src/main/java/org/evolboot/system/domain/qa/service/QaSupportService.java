package org.evolboot.system.domain.qa.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.qa.Qa;
import org.evolboot.system.domain.qa.repository.QaRepository;

/**
 * QA
 *
 * @author evol
 */
@Slf4j
public abstract class QaSupportService {

    protected final QaRepository repository;

    protected QaSupportService(QaRepository repository) {
        this.repository = repository;
    }

    public Qa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(SystemI18nMessage.Qa.notFound()));
    }

}

package org.evolboot.content.domain.qa;

import org.evolboot.content.ContentI18nMessage;
import org.evolboot.content.domain.qa.repository.QaRepository;
import org.evolboot.core.exception.DomainNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * QA
 *
 * @author evol
 * 
 */
@Slf4j
public abstract class QaSupportService {

    final QaRepository repository;

    protected QaSupportService(QaRepository repository) {
        this.repository = repository;
    }

    public Qa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(ContentI18nMessage.Qa.notFound()));
    }

}

package org.evolboot.system.domain.qa.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.qa.repository.QaRepository;
import org.evolboot.system.domain.qa.service.QaSupportService;
import org.springframework.stereotype.Service;

/**
 * QA
 *
 * @author evol
 */
@Service
@Slf4j
public class QaListener {

    private final QaRepository repository;

    private final QaSupportService supportService;

    protected QaListener(QaRepository repository, QaSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

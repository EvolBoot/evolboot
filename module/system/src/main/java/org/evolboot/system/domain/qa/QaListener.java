package org.evolboot.system.domain.qa;

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
public class QaListener extends QaSupportService {

    protected QaListener(QaRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

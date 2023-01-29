package org.evolboot.content.domain.qa;

import org.evolboot.content.domain.qa.repository.QaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * QA
 *
 * @author evol
 * 
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

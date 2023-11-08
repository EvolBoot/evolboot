package org.evolboot.system.domain.qa.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.qa.entity.Qa;
import org.evolboot.system.domain.qa.repository.QaRepository;
import org.springframework.stereotype.Service;

/**
 * QA
 *
 * @author evol
 */
@Slf4j
@Service
public class QaCreateFactory {

    private final QaRepository repository;
    private final QaSupportService supportService;

    protected QaCreateFactory(QaRepository repository, QaSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }


    public Qa execute(Request request) {
        Qa qa = new Qa(
                request.getLocales(),
                request.getEnable(),
                request.getSort(),
                request.getLink()
        );
        repository.save(qa);
        return qa;
    }

    public static class Request extends QaRequestBase {

    }

}

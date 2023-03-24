package org.evolboot.system.domain.qa.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.qa.Qa;
import org.evolboot.system.domain.qa.QaRequestBase;
import org.evolboot.system.domain.qa.repository.QaRepository;
import org.springframework.stereotype.Service;

/**
 * QA
 *
 * @author evol
 * 
 */
@Slf4j
@Service
public class QaCreateFactory extends QaSupportService {
    protected QaCreateFactory(QaRepository repository) {
        super(repository);
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

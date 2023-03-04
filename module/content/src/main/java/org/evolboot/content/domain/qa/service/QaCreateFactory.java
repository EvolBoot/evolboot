package org.evolboot.content.domain.qa.service;

import org.evolboot.content.domain.qa.Qa;
import org.evolboot.content.domain.qa.QaRequestBase;
import org.evolboot.content.domain.qa.repository.QaRepository;
import lombok.extern.slf4j.Slf4j;
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

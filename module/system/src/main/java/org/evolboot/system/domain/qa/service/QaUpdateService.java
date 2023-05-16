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
 */
@Slf4j
@Service
public class QaUpdateService extends QaSupportService {
    protected QaUpdateService(QaRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        Qa qa = findById(id);
        qa.setEnable(request.getEnable());
        qa.setLocales(request.getLocales());
        qa.setLink(request.getLink());
        qa.setSort(request.getSort());
        repository.save(qa);
    }

    public static class Request extends QaRequestBase {
    }

}

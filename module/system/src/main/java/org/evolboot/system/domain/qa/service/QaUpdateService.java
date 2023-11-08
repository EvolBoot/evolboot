package org.evolboot.system.domain.qa.service;

import lombok.Getter;
import lombok.Setter;
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
public class QaUpdateService {

    private final QaRepository repository;
    private final QaSupportService supportService;

    protected QaUpdateService(QaRepository repository, QaSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        Qa qa = supportService.findById(request.getId());
        qa.setEnable(request.getEnable());
        qa.setLocales(request.getLocales());
        qa.setLink(request.getLink());
        qa.setSort(request.getSort());
        repository.save(qa);
    }

    @Getter
    @Setter
    public static class Request extends QaRequestBase {
        private Long id;
    }

}

package org.evolboot.system.domain.qa;

import org.evolboot.system.domain.qa.service.QaCreateFactory;
import org.evolboot.system.domain.qa.service.QaUpdateService;
import org.evolboot.core.data.Page;

import java.util.List;

/**
 * QA
 *
 * @author evol
 * 
 */
public interface QaAppService {

    Qa create(QaCreateFactory.Request request);

    void update(Long id, QaUpdateService.Request request);

    void delete(Long id);

    List<Qa> findAll();

    List<Qa> findAll(QaQuery query);

    Page<Qa> page(QaQuery query);

    Qa findById(Long id);


}

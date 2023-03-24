package org.evolboot.system.domain.qa.repository;

import org.evolboot.system.domain.qa.Qa;
import org.evolboot.system.domain.qa.QaQuery;
import org.evolboot.core.data.Page;

import java.util.List;
import java.util.Optional;

/**
 * QA
 *
 * @author evol
 * 
 */
public interface QaRepository {

    Qa save(Qa qa);

    Optional<Qa> findById(Long id);

    Page<Qa> page(QaQuery query);

    void deleteById(Long id);

    List<Qa> findAll();

    List<Qa> findAll(QaQuery query);
}

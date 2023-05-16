package org.evolboot.system.domain.qa.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.system.domain.qa.Qa;

import java.util.List;
import java.util.Optional;

/**
 * QA
 *
 * @author evol
 */
public interface QaRepository extends BaseRepository<Qa, Long> {

    Qa save(Qa qa);

    Optional<Qa> findById(Long id);


    void deleteById(Long id);

    List<Qa> findAll();

    <Q extends Query> List<Qa> findAll(Q query);

    <Q extends Query> Optional<Qa> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<Qa> page(Q query);

}

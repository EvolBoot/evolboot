package org.evolboot.system.domain.startuppage.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.system.domain.startuppage.StartupPage;

import java.util.List;
import java.util.Optional;

/**
 * 启动页
 *
 * @author evol
 */
public interface StartupPageRepository extends BaseRepository<StartupPage, Long> {

    StartupPage save(StartupPage startupPage);

    Optional<StartupPage> findById(Long id);


    void deleteById(Long id);

    List<StartupPage> findAll();


    <Q extends Query> List<StartupPage> findAll(Q query);

    <Q extends Query> Optional<StartupPage> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<StartupPage> page(Q query);

}

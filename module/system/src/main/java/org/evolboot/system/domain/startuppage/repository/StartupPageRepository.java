package org.evolboot.system.domain.startuppage.repository;

import org.evolboot.system.domain.startuppage.StartupPage;
import org.evolboot.system.domain.startuppage.StartupPageQuery;
import org.evolboot.core.data.Page;

import java.util.List;
import java.util.Optional;

/**
 * 启动页
 *
 * @author evol
 * 
 */
public interface StartupPageRepository {

    StartupPage save(StartupPage startupPage);

    Optional<StartupPage> findById(Long id);

    Page<StartupPage> page(StartupPageQuery query);

    void deleteById(Long id);

    List<StartupPage> findAll();

    List<StartupPage> findAll(StartupPageQuery query);
}

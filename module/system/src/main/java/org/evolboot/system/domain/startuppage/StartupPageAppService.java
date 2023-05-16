package org.evolboot.system.domain.startuppage;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.startuppage.service.StartupPageCreateFactory;
import org.evolboot.system.domain.startuppage.service.StartupPageUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 启动页
 *
 * @author evol
 */
public interface StartupPageAppService {

    StartupPage create(StartupPageCreateFactory.Request request);

    void update(Long id, StartupPageUpdateService.Request request);

    void delete(Long id);

    List<StartupPage> findAll();

    List<StartupPage> findAll(StartupPageQuery query);

    Page<StartupPage> page(StartupPageQuery query);

    StartupPage findById(Long id);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<StartupPage> findOne(StartupPageQuery query);


}

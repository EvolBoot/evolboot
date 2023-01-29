package org.evolboot.content.domain.startuppage;

import org.evolboot.core.data.Page;

import java.util.List;

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


}

package org.evolboot.system.domain.startuppage.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.startuppage.entity.StartupPage;
import org.evolboot.system.domain.startuppage.repository.StartupPageRepository;
import org.springframework.stereotype.Service;

/**
 * 启动页
 *
 * @author evol
 */
@Slf4j
@Service
public class StartupPageCreateFactory extends StartupPageSupportService {
    protected StartupPageCreateFactory(StartupPageRepository repository) {
        super(repository);
    }

    public StartupPage execute(Request request) {
        StartupPage startupPage = new StartupPage(
                request.getSort(),
                request.getEnable(),
                request.getLocales()
        );
        repository.save(startupPage);
        return startupPage;
    }

    public static class Request extends StartupPageRequestBase {

    }

}

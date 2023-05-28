package org.evolboot.system.domain.startuppage.service;

import lombok.Getter;
import lombok.Setter;
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
public class StartupPageUpdateService extends StartupPageSupportService {
    protected StartupPageUpdateService(StartupPageRepository repository) {
        super(repository);
    }

    public void execute( Request request) {
        StartupPage startupPage = findById(request.getId());
        startupPage.setSort(request.getSort());
        startupPage.setEnable(request.getEnable());
        startupPage.setLocales(request.getLocales());
        repository.save(startupPage);
    }

    @Getter
    @Setter
    public static class Request extends StartupPageRequestBase {
        private Long id;
    }

}

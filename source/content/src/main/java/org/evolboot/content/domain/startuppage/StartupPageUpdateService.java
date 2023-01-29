package org.evolboot.content.domain.startuppage;

import org.evolboot.content.domain.startuppage.repository.StartupPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 启动页
 *
 * @author evol
 * 
 */
@Slf4j
@Service
public class StartupPageUpdateService extends StartupPageSupportService {
    protected StartupPageUpdateService(StartupPageRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        StartupPage startupPage = findById(id);
        startupPage.setSort(request.getSort());
        startupPage.setEnable(request.getEnable());
        startupPage.setLocales(request.getLocales());
        repository.save(startupPage);
    }

    public static class Request extends StartupPageRequestBase {
    }

}

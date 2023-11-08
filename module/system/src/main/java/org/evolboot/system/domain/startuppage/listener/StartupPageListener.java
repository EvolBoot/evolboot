package org.evolboot.system.domain.startuppage.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.startuppage.repository.StartupPageRepository;
import org.evolboot.system.domain.startuppage.service.StartupPageSupportService;
import org.springframework.stereotype.Service;

/**
 * 启动页
 *
 * @author evol
 */
@Service
@Slf4j
public class StartupPageListener {

    private final StartupPageRepository repository;

    private final StartupPageSupportService supportService;

    protected StartupPageListener(StartupPageRepository repository, StartupPageSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

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
public class StartupPageListener extends StartupPageSupportService {

    protected StartupPageListener(StartupPageRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

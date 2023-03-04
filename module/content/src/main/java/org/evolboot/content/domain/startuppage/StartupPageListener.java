package org.evolboot.content.domain.startuppage;

import org.evolboot.content.domain.startuppage.repository.StartupPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.content.domain.startuppage.service.StartupPageSupportService;
import org.springframework.stereotype.Service;

/**
 * 启动页
 *
 * @author evol
 * 
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

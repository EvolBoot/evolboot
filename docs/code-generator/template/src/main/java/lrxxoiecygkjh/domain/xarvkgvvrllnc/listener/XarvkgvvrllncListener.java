package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.listener;

import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.XarvkgvvrllncRepository;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncSupportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Service
@Slf4j
public class XarvkgvvrllncListener {

    private final XarvkgvvrllncRepository repository;

    private final XarvkgvvrllncSupportService supportService;

    protected XarvkgvvrllncListener(XarvkgvvrllncRepository repository, XarvkgvvrllncSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

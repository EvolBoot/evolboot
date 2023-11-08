package org.evolboot.system.domain.dictkey.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.dictkey.repository.DictKeyRepository;
import org.evolboot.system.domain.dictkey.service.DictKeySupportService;
import org.springframework.stereotype.Service;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Service
@Slf4j
public class DictKeyListener {

    private final DictKeyRepository repository;

    private final DictKeySupportService supportService;

    protected DictKeyListener(DictKeyRepository repository, DictKeySupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

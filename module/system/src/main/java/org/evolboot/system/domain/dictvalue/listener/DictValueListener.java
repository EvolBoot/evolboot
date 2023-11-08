package org.evolboot.system.domain.dictvalue.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.event.dict.DictKeyDeleteEvent;
import org.evolboot.system.domain.dictvalue.repository.DictValueRepository;
import org.evolboot.system.domain.dictvalue.service.DictValueSupportService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Service
@Slf4j
public class DictValueListener {

    private final DictValueRepository repository;

    private final DictValueSupportService supportService;

    protected DictValueListener(DictValueRepository repository, DictValueSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @EventListener
    @Transactional
    public void on(DictKeyDeleteEvent event) {
        repository.deleteByDictKeyId(event.getId());
    }


}

package org.evolboot.common.domain.dictvalue.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.common.CommonI18nMessage;
import org.evolboot.common.domain.dictvalue.entity.DictValue;
import org.evolboot.common.domain.dictvalue.repository.DictValueRepository;
import org.springframework.stereotype.Service;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Slf4j
@Service
public class DictValueSupportService {

    protected final DictValueRepository repository;

    protected DictValueSupportService(DictValueRepository repository) {
        this.repository = repository;
    }

    public DictValue findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(CommonI18nMessage.DictValue.NOT_FOUND)));
    }

}

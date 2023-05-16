package org.evolboot.system.domain.dictkey.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.core.util.Assert;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.dictkey.DictKey;
import org.evolboot.system.domain.dictkey.repository.DictKeyRepository;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Slf4j
public abstract class DictKeySupportService {

    protected final DictKeyRepository repository;

    protected DictKeySupportService(DictKeyRepository repository) {
        this.repository = repository;
    }

    public DictKey findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(SystemI18nMessage.DictKey.NOT_FOUND)));
    }

    public void keyMustNotFound(String key) {
        Assert.notEmpty(key, "字典Key不能为空");
        Assert.isTrue(repository.findByKey(key).isEmpty(), "字典Key已存在");
    }

}

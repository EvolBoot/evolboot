package org.evolboot.im.domain.groupapply.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.groupapply.GroupApply;
import org.evolboot.im.domain.groupapply.repository.GroupApplyRepository;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Slf4j
public abstract class GroupApplySupportService {

    protected final GroupApplyRepository repository;

    protected GroupApplySupportService(GroupApplyRepository repository) {
        this.repository = repository;
    }

    public GroupApply findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.GroupApply.NOT_FOUND)));
    }

}

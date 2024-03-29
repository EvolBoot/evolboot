package org.evolboot.im.domain.group.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.springframework.stereotype.Service;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Slf4j
@Service
public class GroupSupportService {

    protected final GroupRepository repository;

    protected GroupSupportService(GroupRepository repository) {
        this.repository = repository;
    }

    public Group findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.Group.NOT_FOUND)));
    }

}

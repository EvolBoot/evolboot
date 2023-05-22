package org.evolboot.im.domain.groupmember.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.groupmember.entity.GroupMember;
import org.evolboot.im.domain.groupmember.repository.GroupMemberRepository;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Slf4j
public abstract class GroupMemberSupportService {

    protected final GroupMemberRepository repository;

    protected GroupMemberSupportService(GroupMemberRepository repository) {
        this.repository = repository;
    }

    public GroupMember findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.GroupMember.NOT_FOUND)));
    }

}

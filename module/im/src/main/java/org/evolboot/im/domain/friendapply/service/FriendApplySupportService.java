package org.evolboot.im.domain.friendapply.service;

import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
public abstract class FriendApplySupportService {

    protected final FriendApplyRepository repository;

    protected FriendApplySupportService(FriendApplyRepository repository) {
        this.repository = repository;
    }

    public FriendApply findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.FriendApply.NOT_FOUND)));
    }

}

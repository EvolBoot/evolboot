package org.evolboot.im.domain.friendapply.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.springframework.stereotype.Service;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@Service
public class FriendApplySupportService {

    protected final FriendApplyRepository repository;

    protected FriendApplySupportService(FriendApplyRepository repository) {
        this.repository = repository;
    }

    public FriendApply findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.FriendApply.NOT_FOUND)));
    }

}

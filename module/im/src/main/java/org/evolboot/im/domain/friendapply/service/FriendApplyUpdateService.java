package org.evolboot.im.domain.friendapply.service;

import org.springframework.stereotype.Service;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApplyRequestBase;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@Service
public class FriendApplyUpdateService extends FriendApplySupportService {
    protected FriendApplyUpdateService(FriendApplyRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        FriendApply friendApply = findById(id);
        repository.save(friendApply);
    }

    public static class Request extends FriendApplyRequestBase {
    }

}
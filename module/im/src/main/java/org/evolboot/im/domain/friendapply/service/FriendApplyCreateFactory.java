package org.evolboot.im.domain.friendapply.service;


import org.springframework.stereotype.Service;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.im.domain.friendapply.
        FriendApplyRequestBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@Service
public class FriendApplyCreateFactory extends FriendApplySupportService {
    protected FriendApplyCreateFactory(FriendApplyRepository repository) {
        super(repository);
    }

    public FriendApply execute(Request request) {
        FriendApply friendApply = new FriendApply("test");
        repository.save(friendApply);
        return friendApply;
    }

    public static class Request extends FriendApplyRequestBase {

    }

}

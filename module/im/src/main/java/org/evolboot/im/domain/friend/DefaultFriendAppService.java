package org.evolboot.im.domain.friend;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.friend.service.AllowAddFriendService;
import org.evolboot.im.domain.friend.service.FriendCreateFactory;
import org.evolboot.im.domain.friend.service.FriendSupportService;
import org.evolboot.im.domain.friend.service.FriendUpdateService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Slf4j
@Service
public class DefaultFriendAppService extends FriendSupportService implements FriendAppService {


    private final FriendCreateFactory factory;
    private final FriendUpdateService updateService;

    /**
     * 允许添加好友判断
     */
    private final AllowAddFriendService allowAddFriendService;

    protected DefaultFriendAppService(FriendRepository repository, FriendCreateFactory factory, FriendUpdateService updateService, AllowAddFriendService allowAddFriendService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
        this.allowAddFriendService = allowAddFriendService;
    }

    @Override
    @Transactional
    public Friend create(FriendCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, FriendUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<Friend> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Friend> findAll(FriendQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Friend> page(FriendQuery query) {
        return repository.page(query);
    }

    @Override
    public void allowAddFriend(Long ownerUserId, Long friendUserId) {
        allowAddFriendService.execute(ownerUserId, friendUserId);
    }


}

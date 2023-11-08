package org.evolboot.im.domain.friend;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.friend.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Slf4j
@Service
public class FriendAppServiceImpl implements FriendAppService {


    private final FriendSupportService supportService;

    private final FriendRepository repository;

    private final FriendCreateFactory factory;
    private final FriendUpdateService updateService;

    private final FriendApplyService applyService;

    private final FriendDeleteService deleteService;

    private final FriendBlacklistService blacklistService;


    protected FriendAppServiceImpl(FriendRepository repository, FriendSupportService supportService, FriendCreateFactory factory, FriendUpdateService updateService, FriendApplyService applyService, FriendDeleteService deleteService, FriendBlacklistService blacklistService) {
        this.repository = repository;
        this.supportService = supportService;
        this.factory = factory;
        this.updateService = updateService;
        this.applyService = applyService;
        this.deleteService = deleteService;
        this.blacklistService = blacklistService;
    }

    @Override
    @Transactional
    public Friend create(FriendCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(FriendUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        supportService.findById(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Friend apply(FriendApplyService.Request request) {
        return applyService.execute(request);
    }


    @Override
    @Transactional
    public void joinBlacklist(Long ownerUserId, Long friendUserId) {
        blacklistService.joinBlacklist(ownerUserId, friendUserId);

    }

    @Override
    @Transactional
    public void removeBlacklist(Long ownerUserId, Long friendUserId) {
        blacklistService.removeBlacklist(ownerUserId, friendUserId);
    }

    @Override
    @Transactional
    public void deleteByFriendUserId(Long ownerUserId, Long friendUserId) {
        deleteService.deleteByFriendUserId(ownerUserId, friendUserId);
    }

}

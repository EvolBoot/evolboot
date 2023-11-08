package org.evolboot.im.domain.friend;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.im.ImAccessAuthorities;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.friend.service.FriendCreateFactory;
import org.evolboot.im.domain.friend.service.FriendSupportService;
import org.evolboot.im.domain.friend.service.FriendUpdateService;

import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.service.FriendQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 好友关系
 *
 * @author evol
 * @date 2023-06-14 19:58:06
 */
@Slf4j
@Service
public class FriendQueryServiceImpl  implements FriendQueryService {

    private final FriendRepository repository;
    private final FriendSupportService supportService;

    protected FriendQueryServiceImpl(FriendRepository repository, FriendSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public Friend findById(Long id) {
        return supportService.findById(id);
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
    public Optional<Friend> findOne(FriendQuery query) {
        return repository.findOne(query);
    }
}

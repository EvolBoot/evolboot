package org.evolboot.im.domain.friendapply;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.im.ImAccessAuthorities;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.service.FriendApplyCreateFactory;
import org.evolboot.im.domain.friendapply.service.FriendApplySupportService;
import org.evolboot.im.domain.friendapply.service.FriendApplyUpdateService;

import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.service.FriendApplyQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 好友申请
 *
 * @author evol
 * @date 2023-06-14 20:01:33
 */
@Slf4j
@Service
public class FriendApplyQueryServiceImpl extends FriendApplySupportService implements FriendApplyQueryService {

    protected FriendApplyQueryServiceImpl(FriendApplyRepository repository) {
        super(repository);
    }


    @Override
    public List<FriendApply> findAll() {
        return repository.findAll();
    }


    @Override
    public List<FriendApply> findAll(FriendApplyQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<FriendApply> page(FriendApplyQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<FriendApply> findOne(FriendApplyQuery query) {
        return repository.findOne(query);
    }
}

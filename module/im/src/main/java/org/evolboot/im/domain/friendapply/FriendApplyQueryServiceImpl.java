package org.evolboot.im.domain.friendapply;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.service.FriendApplySupportService;

import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.dto.FriendApplyQueryRequest;


import org.springframework.stereotype.Service;
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
public class FriendApplyQueryServiceImpl  implements FriendApplyQueryService {

    private final FriendApplyRepository repository;
    private final FriendApplySupportService supportService;

    protected FriendApplyQueryServiceImpl(FriendApplyRepository repository, FriendApplySupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public FriendApply findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    public List<FriendApply> findAll() {
        return repository.findAll();
    }


    @Override
    public List<FriendApply> findAll(FriendApplyQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<FriendApply> page(FriendApplyQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<FriendApply> findOne(FriendApplyQueryRequest query) {
        return repository.findOne(query);
    }
}

package org.evolboot.im.domain.groupapply;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.im.ImAccessAuthorities;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.groupapply.repository.GroupApplyRepository;
import org.evolboot.im.domain.groupapply.service.GroupApplyCreateFactory;
import org.evolboot.im.domain.groupapply.service.GroupApplySupportService;
import org.evolboot.im.domain.groupapply.service.GroupApplyUpdateService;

import org.evolboot.im.domain.groupapply.entity.GroupApply;
import org.evolboot.im.domain.groupapply.service.GroupApplyQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 群申请
 *
 * @author evol
 * @date 2023-06-14 20:04:27
 */
@Slf4j
@Service
public class GroupApplyQueryServiceImpl implements GroupApplyQueryService {

    private final GroupApplyRepository repository;
    private final GroupApplySupportService supportService;

    protected GroupApplyQueryServiceImpl(GroupApplyRepository repository, GroupApplySupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public GroupApply findById(Long id) {
        return supportService.findById(id);
    }


    @Override
    public List<GroupApply> findAll() {
        return repository.findAll();
    }


    @Override
    public List<GroupApply> findAll(GroupApplyQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<GroupApply> page(GroupApplyQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<GroupApply> findOne(GroupApplyQuery query) {
        return repository.findOne(query);
    }
}

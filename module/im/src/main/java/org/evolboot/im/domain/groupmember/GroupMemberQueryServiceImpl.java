package org.evolboot.im.domain.groupmember;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.groupmember.repository.GroupMemberRepository;
import org.evolboot.im.domain.groupmember.service.GroupMemberSupportService;

import org.evolboot.im.domain.groupmember.entity.GroupMember;
import org.evolboot.im.domain.groupmember.dto.GroupMemberQueryRequest;


import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 群成员
 *
 * @author evol
 * @date 2023-06-14 20:05:56
 */
@Slf4j
@Service
public class GroupMemberQueryServiceImpl  implements GroupMemberQueryService {

    private final GroupMemberRepository repository;
    private final GroupMemberSupportService supportService;

    protected GroupMemberQueryServiceImpl(GroupMemberRepository repository, GroupMemberSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public GroupMember findById(Long id) {
        return supportService.findById(id);
    }
    @Override
    public List<GroupMember> findAll() {
        return repository.findAll();
    }


    @Override
    public List<GroupMember> findAll(GroupMemberQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<GroupMember> page(GroupMemberQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<GroupMember> findOne(GroupMemberQueryRequest query) {
        return repository.findOne(query);
    }
}

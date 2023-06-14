package org.evolboot.im.domain.groupmember;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.im.ImAccessAuthorities;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.groupmember.repository.GroupMemberRepository;
import org.evolboot.im.domain.groupmember.service.GroupMemberCreateFactory;
import org.evolboot.im.domain.groupmember.service.GroupMemberSupportService;
import org.evolboot.im.domain.groupmember.service.GroupMemberUpdateService;

import org.evolboot.im.domain.groupmember.entity.GroupMember;
import org.evolboot.im.domain.groupmember.service.GroupMemberQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class GroupMemberQueryServiceImpl extends GroupMemberSupportService implements GroupMemberQueryService {

    protected GroupMemberQueryServiceImpl(GroupMemberRepository repository) {
        super(repository);
    }


    @Override
    public List<GroupMember> findAll() {
        return repository.findAll();
    }


    @Override
    public List<GroupMember> findAll(GroupMemberQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<GroupMember> page(GroupMemberQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<GroupMember> findOne(GroupMemberQuery query) {
        return repository.findOne(query);
    }
}

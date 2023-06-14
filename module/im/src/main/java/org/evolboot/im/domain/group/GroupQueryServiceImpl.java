package org.evolboot.im.domain.group;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.im.ImAccessAuthorities;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.evolboot.im.domain.group.service.GroupCreateFactory;
import org.evolboot.im.domain.group.service.GroupSupportService;
import org.evolboot.im.domain.group.service.GroupUpdateService;

import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.service.GroupQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 群组
 *
 * @author evol
 * @date 2023-06-14 20:03:00
 */
@Slf4j
@Service
public class GroupQueryServiceImpl extends GroupSupportService implements GroupQueryService {

    protected GroupQueryServiceImpl(GroupRepository repository) {
        super(repository);
    }


    @Override
    public List<Group> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Group> findAll(GroupQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Group> page(GroupQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<Group> findOne(GroupQuery query) {
        return repository.findOne(query);
    }
}

package org.evolboot.im.domain.group;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.evolboot.im.domain.group.service.GroupSupportService;

import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.dto.GroupQueryRequest;


import org.springframework.stereotype.Service;
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
public class GroupQueryServiceImpl  implements GroupQueryService {

    private final GroupRepository repository;
    private final GroupSupportService supportService;

    protected GroupQueryServiceImpl(GroupRepository repository, GroupSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public Group findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    public List<Group> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Group> findAll(GroupQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Group> page(GroupQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<Group> findOne(GroupQueryRequest query) {
        return repository.findOne(query);
    }
}

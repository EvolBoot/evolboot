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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Slf4j
@Service
public class DefaultGroupAppService extends GroupSupportService implements GroupAppService {


    private final GroupCreateFactory factory;
    private final GroupUpdateService updateService;

    protected DefaultGroupAppService(GroupRepository repository, GroupCreateFactory factory, GroupUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public Group create(GroupCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, GroupUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
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

}

package org.evolboot.im.domain.group;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.evolboot.im.domain.group.service.GroupCreateFactory;
import org.evolboot.im.domain.group.service.GroupQuery;
import org.evolboot.im.domain.group.service.GroupSupportService;
import org.evolboot.im.domain.group.service.GroupUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Slf4j
@Service
public class GroupAppServiceImpl extends GroupSupportService implements GroupAppService {


    private final GroupCreateFactory factory;
    private final GroupUpdateService updateService;

    protected GroupAppServiceImpl(GroupRepository repository, GroupCreateFactory factory, GroupUpdateService updateService) {
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
    public void update(GroupUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

}

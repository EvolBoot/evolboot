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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Slf4j
@Service
public class DefaultGroupApplyAppService extends GroupApplySupportService implements GroupApplyAppService {


    private final GroupApplyCreateFactory factory;
    private final GroupApplyUpdateService updateService;

    protected DefaultGroupApplyAppService(GroupApplyRepository repository, GroupApplyCreateFactory factory, GroupApplyUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public GroupApply create(GroupApplyCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, GroupApplyUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
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

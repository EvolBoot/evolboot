package org.evolboot.im.domain.friendapply;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.service.FriendApplyAuditService;
import org.evolboot.im.domain.friendapply.service.FriendApplyCreateFactory;
import org.evolboot.im.domain.friendapply.service.FriendApplySupportService;
import org.evolboot.im.domain.friendapply.service.FriendApplyUpdateService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@Service
public class DefaultFriendApplyAppService extends FriendApplySupportService implements FriendApplyAppService {


    private final FriendApplyCreateFactory factory;
    private final FriendApplyUpdateService updateService;

    private final FriendApplyAuditService applyAuditService;

    protected DefaultFriendApplyAppService(FriendApplyRepository repository, FriendApplyCreateFactory factory, FriendApplyUpdateService updateService, FriendApplyAuditService applyAuditService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
        this.applyAuditService = applyAuditService;
    }

    @Override
    @Transactional
    public FriendApply create(FriendApplyCreateFactory.Request request) {
        return factory.execute(request);
    }

    @Override
    @Transactional
    public FriendApply audit(FriendApplyAuditService.Request request) {
        return applyAuditService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
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

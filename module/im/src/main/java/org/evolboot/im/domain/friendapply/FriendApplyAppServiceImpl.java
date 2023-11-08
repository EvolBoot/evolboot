package org.evolboot.im.domain.friendapply;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@Service
public class FriendApplyAppServiceImpl implements FriendApplyAppService {

    private final FriendApplySupportService supportService;

    private final FriendApplyRepository repository;


    private final FriendApplyCreateFactory factory;
    private final FriendApplyUpdateService updateService;

    private final FriendApplyAuditService applyAuditService;

    protected FriendApplyAppServiceImpl(FriendApplyRepository repository, FriendApplyCreateFactory factory, FriendApplyUpdateService updateService, FriendApplyAuditService applyAuditService, FriendApplySupportService supportService) {
        this.supportService = supportService;
        this.repository = repository;
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
        supportService.findById(id);
        repository.deleteById(id);
    }


}

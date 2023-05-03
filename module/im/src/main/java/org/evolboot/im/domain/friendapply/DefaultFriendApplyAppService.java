package org.evolboot.im.domain.friendapply;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.im.ImAccessAuthorities;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.service.FriendApplyCreateFactory;
import org.evolboot.im.domain.friendapply.service.FriendApplySupportService;
import org.evolboot.im.domain.friendapply.service.FriendApplyUpdateService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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

    protected DefaultFriendApplyAppService(FriendApplyRepository repository, FriendApplyCreateFactory factory, FriendApplyUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public FriendApply create(FriendApplyCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, FriendApplyUpdateService.Request request) {
        updateService.execute(id, request);
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

}

package org.evolboot.im.domain.userconversation;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;
import org.evolboot.im.domain.userconversation.service.UserConversationCreateFactory;
import org.evolboot.im.domain.userconversation.service.UserConversationSupportService;
import org.evolboot.im.domain.userconversation.service.UserConversationUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Slf4j
@Service
public class DefaultUserConversationAppService extends UserConversationSupportService implements UserConversationAppService {


    private final UserConversationCreateFactory factory;
    private final UserConversationUpdateService updateService;

    protected DefaultUserConversationAppService(UserConversationRepository repository, UserConversationCreateFactory factory, UserConversationUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public UserConversation create(UserConversationCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, UserConversationUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<UserConversation> findAll() {
        return repository.findAll();
    }


    @Override
    public List<UserConversation> findAll(UserConversationQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<UserConversation> page(UserConversationQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<UserConversation> findOne(UserConversationQuery query) {
        return repository.findOne(query);
    }

}

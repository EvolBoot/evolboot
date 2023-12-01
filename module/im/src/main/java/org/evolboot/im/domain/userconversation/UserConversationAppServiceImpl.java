package org.evolboot.im.domain.userconversation;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.userconversation.entity.UserConversation;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;
import org.evolboot.im.domain.userconversation.service.UserConversationCreateFactory;
import org.evolboot.im.domain.userconversation.service.UserConversationSupportService;
import org.evolboot.im.domain.userconversation.service.UserConversationUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Slf4j
@Service
public class UserConversationAppServiceImpl  implements UserConversationAppService {


    private final UserConversationCreateFactory factory;
    private final UserConversationUpdateService updateService;

    private final UserConversationRepository repository;

    private final UserConversationSupportService supportService;

    protected UserConversationAppServiceImpl(UserConversationRepository repository, UserConversationCreateFactory factory, UserConversationUpdateService updateService, UserConversationSupportService supportService) {
        this.factory = factory;
        this.updateService = updateService;
        this.repository = repository;
        this.supportService = supportService;
    }

    public UserConversation findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    @Transactional
    public UserConversation create(UserConversationCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(UserConversationUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


}

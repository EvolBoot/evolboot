package org.evolboot.im.domain.userconversation.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.userconversation.dto.UserConversationRequestBase;
import org.evolboot.im.domain.userconversation.entity.UserConversation;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;
import org.springframework.stereotype.Service;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Slf4j
@Service
public class UserConversationUpdateService {

    private final UserConversationRepository repository;
    private final UserConversationSupportService supportService;

    protected UserConversationUpdateService(UserConversationRepository repository, UserConversationSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        UserConversation userConversation = supportService.findById(request.getId());
        repository.save(userConversation);
    }

    @Getter
    @Setter
    public static class Request extends UserConversationRequestBase {
        private Long id;
    }

}

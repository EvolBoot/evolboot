package org.evolboot.im.domain.userconversation.service;


import org.springframework.stereotype.Service;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;
import org.evolboot.im.domain.userconversation.UserConversation;
import org.evolboot.im.domain.userconversation.
        UserConversationRequestBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Slf4j
@Service
public class UserConversationCreateFactory extends UserConversationSupportService {
    protected UserConversationCreateFactory(UserConversationRepository repository) {
        super(repository);
    }

    public UserConversation execute(Request request) {
        UserConversation userConversation = new UserConversation("test");
        repository.save(userConversation);
        return userConversation;
    }

    public static class Request extends UserConversationRequestBase {

    }

}

package org.evolboot.im.domain.userconversation;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.im.ImAccessAuthorities;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;
import org.evolboot.im.domain.userconversation.service.UserConversationCreateFactory;
import org.evolboot.im.domain.userconversation.service.UserConversationSupportService;
import org.evolboot.im.domain.userconversation.service.UserConversationUpdateService;

import org.evolboot.im.domain.userconversation.entity.UserConversation;
import org.evolboot.im.domain.userconversation.service.UserConversationQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 用户会话
 *
 * @author evol
 * @date 2023-06-14 20:07:28
 */
@Slf4j
@Service
public class UserConversationQueryServiceImpl extends UserConversationSupportService implements UserConversationQueryService {

    protected UserConversationQueryServiceImpl(UserConversationRepository repository) {
        super(repository);
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

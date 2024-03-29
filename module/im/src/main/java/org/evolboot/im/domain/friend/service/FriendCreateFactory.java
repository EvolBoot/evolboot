package org.evolboot.im.domain.friend.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.util.Assert;
import org.evolboot.im.acl.client.UserClient;
import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.ConversationAppService;
import org.evolboot.im.domain.conversation.service.ConversationCreateFactory;
import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.shared.event.im.FriendCreateEvent;
import org.springframework.stereotype.Service;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Slf4j
@Service
//TODO 多语言
public class FriendCreateFactory {

    private final FriendSupportService supportService;

    private final FriendRepository repository;

    private final ConversationAppService conversationAppService;

    private final UserClient userClient;

    private final EventPublisher eventPublisher;


    protected FriendCreateFactory(FriendRepository repository, FriendSupportService supportService, ConversationAppService conversationAppService, UserClient userClient, EventPublisher eventPublisher) {
        this.repository = repository;
        this.supportService = supportService;
        this.conversationAppService = conversationAppService;
        this.userClient = userClient;
        this.eventPublisher = eventPublisher;
    }

    public Friend execute(Request request) {
        // 判断用户是否存在
        Assert.isTrue(userClient.existsByUserId(request.getFriendUserId()), "用户不存在");
        Assert.isTrue(userClient.existsByUserId(request.getOwnerUserId()), "用户不存在");
        // 创建会话
        Conversation conversation = conversationAppService.create(new ConversationCreateFactory.Request(ConversationType.SINGLE, supportService.buildConversationId(request.getOwnerUserId(), request.getFriendUserId())));

        Friend owner = repository.findByOwnerUserIdAndFriendUserId(request.getOwnerUserId(), request.getFriendUserId()).orElseGet(() -> new Friend(
                request.getOwnerUserId(),
                request.getFriendUserId(),
                conversation.id()
        ));
        Friend friend = repository.findByOwnerUserIdAndFriendUserId(request.getFriendUserId(), request.getOwnerUserId()).orElseGet(() -> new Friend(
                request.getFriendUserId(),
                request.getOwnerUserId(),
                conversation.id()
        ));
        repository.save(friend);
        repository.save(owner);
        // 发出事件
        eventPublisher.publishEvent(new FriendCreateEvent(owner.id(), owner.getOwnerUserId(), owner.getFriendUserId(), conversation.id()));
        return owner;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        /**
         * 所有者ID
         */
        private Long ownerUserId;

        /**
         * 好友ID
         */
        private Long friendUserId;
    }

}

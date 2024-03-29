package org.evolboot.im.domain.userconversation.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.im.domain.userconversation.entity.UserConversationForbidTalkCause;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;
import org.evolboot.im.domain.userconversation.service.UserConversationCreateFactory;
import org.evolboot.im.domain.userconversation.service.UserConversationDeleteService;
import org.evolboot.im.domain.userconversation.service.UserConversationForbidTalkCausesChangeService;
import org.evolboot.im.domain.userconversation.service.UserConversationSupportService;
import org.evolboot.shared.event.im.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Service
@Slf4j
public class UserConversationListener  {

    
    private final UserConversationCreateFactory factory;
    private final UserConversationForbidTalkCausesChangeService forbidTalkCausesChangeService;

    private final UserConversationDeleteService deleteService;
    
    private final UserConversationSupportService supportService;
    
    private final UserConversationRepository repository;
    

    protected UserConversationListener(UserConversationRepository repository, UserConversationCreateFactory factory, UserConversationForbidTalkCausesChangeService forbidTalkCausesChangeService, UserConversationDeleteService deleteService, UserConversationSupportService supportService) {
        this.repository = repository;
        this.factory = factory;
        this.forbidTalkCausesChangeService = forbidTalkCausesChangeService;
        this.deleteService = deleteService;
        this.supportService = supportService;
    }


    @EventListener
    public void on(FriendCreateEvent event) {
        log.info("监听到添加好友关系:{},{},{}", event.getOwnerUserId(), event.getFriendUserId(), event.getConversationId());
        factory.execute(new UserConversationCreateFactory.Request(event.getOwnerUserId(), event.getConversationId(), ConversationType.SINGLE, null, event.getFriendUserId()));
        factory.execute(new UserConversationCreateFactory.Request(event.getFriendUserId(), event.getConversationId(), ConversationType.SINGLE, null, event.getOwnerUserId()));
        // 移除禁言
        forbidTalkCausesChangeService.removeForbidTalkCauses(event.getFriendUserId(), event.getConversationId(), UserConversationForbidTalkCause.BE_DELETE_RELATION);
        forbidTalkCausesChangeService.removeForbidTalkCauses(event.getFriendUserId(), event.getConversationId(), UserConversationForbidTalkCause.SINGLE_BE_BLACKLIST);

    }

    @EventListener
    public void on(FriendDeleteEvent event) {
        log.info("监听到删除好友关系:{},{},{}", event.getOwnerUserId(), event.getFriendUserId(), event.getConversationId());
        deleteService.execute(event.getOwnerUserId(), event.getConversationId());
        forbidTalkCausesChangeService.addForbidTalkCauses(event.getFriendUserId(), event.getConversationId(), UserConversationForbidTalkCause.BE_DELETE_RELATION);
    }


    @EventListener
    public void on(FriendJoinBlacklistEvent event) {
        log.info("监听到将好友加入黑名单:{},{},{}", event.getOwnerUserId(), event.getFriendUserId(), event.getConversationId());
        forbidTalkCausesChangeService.addForbidTalkCauses(event.getOwnerUserId(), event.getConversationId(), UserConversationForbidTalkCause.SINGLE_BLACKLIST);
        forbidTalkCausesChangeService.addForbidTalkCauses(event.getFriendUserId(), event.getConversationId(), UserConversationForbidTalkCause.SINGLE_BE_BLACKLIST);
    }

    @EventListener
    public void on(FriendRemoveBlacklistEvent event) {
        log.info("监听到将好友移出黑名单:{},{},{}", event.getOwnerUserId(), event.getFriendUserId(), event.getConversationId());
        forbidTalkCausesChangeService.removeForbidTalkCauses(event.getOwnerUserId(), event.getConversationId(), UserConversationForbidTalkCause.SINGLE_BLACKLIST);
        forbidTalkCausesChangeService.removeForbidTalkCauses(event.getFriendUserId(), event.getConversationId(), UserConversationForbidTalkCause.SINGLE_BE_BLACKLIST);
    }


    @EventListener
    public void on(GroupMemberCreateEvent event) {
        log.info("监听到群员增加创建:{},{},{}", event.getMemberUserId(), event.getGroupId(), event.getConversationId());
        factory.execute(new UserConversationCreateFactory.Request(event.getMemberUserId(), event.getConversationId(), ConversationType.GROUP, event.getGroupId(), null));
    }


}

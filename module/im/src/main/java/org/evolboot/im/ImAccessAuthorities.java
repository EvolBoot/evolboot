package org.evolboot.im;


import static org.evolboot.security.api.access.AccessAuthorities.*;

/**
 * 权限控制标识符
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
public interface ImAccessAuthorities {


    /**
     * 会话
     */
    interface Conversation {
        String HAS_CREATE = AUTHORITY_PREFIX + "im_conversation_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "im_conversation_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "im_conversation_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "im_conversation_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "im_conversation_single" + AUTHORITY_SUFFIX;
    }


    /**
     * 用户会话
     */
    interface UserConversation {
        String HAS_CREATE = AUTHORITY_PREFIX + "im_userconversation_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "im_userconversation_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "im_userconversation_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "im_userconversation_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "im_userconversation_single" + AUTHORITY_SUFFIX;
    }


    /**
     * 聊天记录
     */
    interface ChatRecord {
        String HAS_CREATE = AUTHORITY_PREFIX + "im_chatrecord_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "im_chatrecord_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "im_chatrecord_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "im_chatrecord_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "im_chatrecord_single" + AUTHORITY_SUFFIX;
    }


    /**
     * 群组
     */
    interface Group {
        String HAS_CREATE = AUTHORITY_PREFIX + "im_group_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "im_group_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "im_group_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "im_group_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "im_group_single" + AUTHORITY_SUFFIX;
    }

    /**
     * 群成员
     */
    interface GroupMember {
        String HAS_CREATE = AUTHORITY_PREFIX + "im_groupmember_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "im_groupmember_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "im_groupmember_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "im_groupmember_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "im_groupmember_single" + AUTHORITY_SUFFIX;
    }

    /**
     * 群申请
     */
    interface GroupApply {
        String HAS_CREATE = AUTHORITY_PREFIX + "im_groupapply_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "im_groupapply_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "im_groupapply_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "im_groupapply_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "im_groupapply_single" + AUTHORITY_SUFFIX;
    }

    /**
     * 好友关系
     */
    interface Friend {
        String HAS_CREATE = AUTHORITY_PREFIX + "im_friend_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "im_friend_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "im_friend_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "im_friend_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "im_friend_single" + AUTHORITY_SUFFIX;
    }

    /**
     * 好友申请
     */
    interface FriendApply {
        String HAS_CREATE = AUTHORITY_PREFIX + "im_friendapply_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "im_friendapply_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "im_friendapply_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "im_friendapply_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "im_friendapply_single" + AUTHORITY_SUFFIX;
    }


    /**
     * 产品
     */
    interface Friendship {
        String HAS_CREATE = AUTHORITY_PREFIX + "im_friendship_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "im_friendship_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "im_friendship_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "im_friendship_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "im_friendship_single" + AUTHORITY_SUFFIX;
    }



}

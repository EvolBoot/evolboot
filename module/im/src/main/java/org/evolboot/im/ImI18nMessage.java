package org.evolboot.im;

import org.evolboot.core.exception.ErrCodeMsg;
import org.evolboot.core.i18n.I18NMessageHolder;

import static org.evolboot.im.ImErrorCode.*;

/**
 * 国际化
 * TODO 多语言
 * @author evol
 * @date 2023-05-02 21:43:03
 */
public abstract class ImI18nMessage {

    public static final String NAMESPACE = "im";


    /**
     * 会话
     */
    public static class Conversation {
        public static final String NAMESPACE = ImI18nMessage.NAMESPACE + ".conversation";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }


    /**
     * 用户会话
     */
    public static class UserConversation {
        public static final String NAMESPACE = ImI18nMessage.NAMESPACE + ".userconversation";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }


    /**
     * 聊天记录
     */
    public static class ChatRecord {
        public static final String NAMESPACE = ImI18nMessage.NAMESPACE + ".chatrecord";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }


    /**
     * 群组
     */
    public static class Group {
        public static final String NAMESPACE = ImI18nMessage.NAMESPACE + ".group";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }


    /**
     * 群成员
     */
    public static class GroupMember {
        public static final String NAMESPACE = ImI18nMessage.NAMESPACE + ".groupmember";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }


    /**
     * 群申请
     */
    public static class GroupApply {
        public static final String NAMESPACE = ImI18nMessage.NAMESPACE + ".groupapply";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }


    /**
     * 好友关系
     */
    public static class Friend {
        public static final String NAMESPACE = ImI18nMessage.NAMESPACE + ".friend";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static final String USER_NOT_FOUND = "用户不存在";
        public static final String YOU_CANNOT_ADD_YOURSELF_AS_A_FRIEND = "不能添加自己为好友";

        public static ErrCodeMsg haveBeenFriends() {
            return ErrCodeMsg.of(HAVE_BEEN_FRIENDS, "你们已经是好友了");
        }

        public static ErrCodeMsg  alreadyOnYourBlacklist() {
            return ErrCodeMsg.of(ALREADY_ON_YOUR_BLACKLIST, "Ta已经被你拉黑了");
        }
        public static ErrCodeMsg  hasBeenBlockedByFriends() {
            return ErrCodeMsg.of(HAS_BEEN_BLOCKED_BY_FRIENDS, "你已经被Ta拉黑了");
        }

    }


    /**
     * 好友申请
     */
    public static class FriendApply {
        public static final String NAMESPACE = ImI18nMessage.NAMESPACE + ".friendapply";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";




    }

}

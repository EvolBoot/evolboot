package org.evolboot.im.domain.userconversation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

/**
 * 禁言原因
 *
 * @author evol
 */
@Getter
@AllArgsConstructor
public enum UserConversationForbidTalkCause {


    /**
     * 拉黑,不能说话
     */

    SINGLE_BLACKLIST(1),

    /**
     * 被加入黑名单
     */
    SINGLE_BE_BLACKLIST(2),

    /**
     * 被群禁言
     */
    GROUP_FORBID_TALK(4),

    /**
     * 被单方面删除
     */
    BE_DELETE_RELATION(8);

    private final Integer value;

    private static final UserConversationForbidTalkCause[] VALUES = values();


    public static int convertToSymbol(Set<UserConversationForbidTalkCause> userIdentities) {
        int identitySymbol = 0;
        for (UserConversationForbidTalkCause userConversationForbidTalkCause : userIdentities) {
            identitySymbol |= userConversationForbidTalkCause.value;
        }
        return identitySymbol;
    }

    public static Set<UserConversationForbidTalkCause> convertEnum(int identitySymbol) {
        EnumSet<UserConversationForbidTalkCause> userIdentities = EnumSet.noneOf(UserConversationForbidTalkCause.class);
        for (UserConversationForbidTalkCause userConversationForbidTalkCause : VALUES) {
            if ((identitySymbol & userConversationForbidTalkCause.getValue()) != 0) {
                userIdentities.add(userConversationForbidTalkCause);
            }
        }
        return userIdentities;
    }


}

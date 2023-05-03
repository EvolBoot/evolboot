package org.evolboot.im.domain.userconversation;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public enum UserConversationStatus {

    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 拉黑,不能说话
     */
    BLOCK(1),
    /**
     * 被禁言
     */
    FORBID_TALK(2),

    /**
     * 删除关系
     */
    DELETE_RELATION(3)
    ;
    private final Integer value;

    private static final Map<Integer, UserConversationStatus> VALUES = Maps.newHashMapWithExpectedSize(UserConversationStatus.values().length);

    static {
        Arrays.stream(UserConversationStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static UserConversationStatus convertTo(Integer value) {
        return VALUES.get(value);
    }


}

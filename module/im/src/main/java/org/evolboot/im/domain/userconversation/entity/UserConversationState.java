package org.evolboot.im.domain.userconversation.entity;

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
public enum UserConversationState {

    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 禁言
     */
    FORBID_TALK(1),

    ;
    private final Integer value;

    private static final Map<Integer, UserConversationState> VALUES = Maps.newHashMapWithExpectedSize(UserConversationState.values().length);

    static {
        Arrays.stream(UserConversationState.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static UserConversationState convertTo(Integer value) {
        return VALUES.get(value);
    }


}

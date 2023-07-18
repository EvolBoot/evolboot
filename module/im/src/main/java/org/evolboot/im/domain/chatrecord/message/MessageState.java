package org.evolboot.im.domain.chatrecord.message;

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
public enum MessageState {

    NORMAL(0),
    DELETE(1),
    REVOKE(2);
    private final Integer value;

    private static final Map<Integer, MessageState> VALUES = Maps.newHashMapWithExpectedSize(MessageState.values().length);

    static {
        Arrays.stream(MessageState.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static MessageState convertTo(Integer value) {
        return VALUES.get(value);
    }


}

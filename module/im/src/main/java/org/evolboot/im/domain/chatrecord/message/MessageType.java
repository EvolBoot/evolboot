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
public enum MessageType {

    TEXT(0),
    IMAGE(1),
    VOICE(2),
    VIDEO(3),
    UNKNOWN(127),

    ;
    private final Integer value;

    private static final Map<Integer, MessageType> VALUES = Maps.newHashMapWithExpectedSize(MessageType.values().length);

    static {
        Arrays.stream(MessageType.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static MessageType convertTo(Integer value) {
        return VALUES.get(value);
    }


}

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
public enum MessageStatus {

    NORMAL(0),
    DELETE(1),
    REVOKE(2);
    private final Integer value;

    private static final Map<Integer, MessageStatus> VALUES = Maps.newHashMapWithExpectedSize(MessageStatus.values().length);

    static {
        Arrays.stream(MessageStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static MessageStatus convertTo(Integer value) {
        return VALUES.get(value);
    }


}

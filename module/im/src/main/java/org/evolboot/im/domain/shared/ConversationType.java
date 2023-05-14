package org.evolboot.im.domain.shared;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * 会话类型
 *
 * @author evol
 */
@Getter
@AllArgsConstructor
public enum ConversationType {


    /**
     * 群聊
     */
    GROUP(0),

    /**
     * 私聊
     */
    SINGLE(1);


    private final Integer value;

    private static final Map<Integer, ConversationType> VALUES = Maps.newHashMapWithExpectedSize(ConversationType.values().length);

    static {
        Arrays.stream(ConversationType.values()).forEach(e -> {
            VALUES.put(e.value, e);
        });
    }

    public static ConversationType convertTo(Integer value) {
        return VALUES.get(value);
    }
}

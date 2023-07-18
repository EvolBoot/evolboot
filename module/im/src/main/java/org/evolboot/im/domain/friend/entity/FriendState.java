package org.evolboot.im.domain.friend.entity;

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
public enum FriendState {

    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 拉黑
     */
    BLACKLIST(1),

    ;
    private final Integer value;

    private static final Map<Integer, FriendState> VALUES = Maps.newHashMapWithExpectedSize(FriendState.values().length);

    static {
        Arrays.stream(FriendState.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static FriendState convertTo(Integer value) {
        return VALUES.get(value);
    }


}

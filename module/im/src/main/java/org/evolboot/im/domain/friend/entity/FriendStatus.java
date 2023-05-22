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
public enum FriendStatus {

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

    private static final Map<Integer, FriendStatus> VALUES = Maps.newHashMapWithExpectedSize(FriendStatus.values().length);

    static {
        Arrays.stream(FriendStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static FriendStatus convertTo(Integer value) {
        return VALUES.get(value);
    }


}

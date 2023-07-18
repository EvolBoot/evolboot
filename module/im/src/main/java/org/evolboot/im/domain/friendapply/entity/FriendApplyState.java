package org.evolboot.im.domain.friendapply.entity;

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
public enum FriendApplyState {

    /**
     * 待处理
     */
    PENDING(0),
    /**
     * 拒绝
     */
    REFUSE(1),
    /**
     * 同意
     */
    AGREE(2),

    /**
     * 自动同意
     */
    AUTO_AGREE(3),

    /**
     * 过期
     */
    EXPIRE(4);


    private final Integer value;

    private static final Map<Integer, FriendApplyState> VALUES = Maps.newHashMapWithExpectedSize(FriendApplyState.values().length);

    static {
        Arrays.stream(FriendApplyState.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static FriendApplyState convertTo(Integer value) {
        return VALUES.get(value);
    }


}

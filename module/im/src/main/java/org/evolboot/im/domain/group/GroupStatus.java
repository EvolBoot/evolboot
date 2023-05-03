package org.evolboot.im.domain.group;

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
public enum GroupStatus {

    /**
     * 可自由假如
     */
    FREE_JOIN(0),
    /**
     * 需要申请加入
     */
    NEED_APPLY(1),
    /**
     * 禁止申请
     */
    FORBID_APPLY(2),

    ;
    private final Integer value;

    private static final Map<Integer, GroupStatus> VALUES = Maps.newHashMapWithExpectedSize(GroupStatus.values().length);

    static {
        Arrays.stream(GroupStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static GroupStatus convertTo(Integer value) {
        return VALUES.get(value);
    }


}

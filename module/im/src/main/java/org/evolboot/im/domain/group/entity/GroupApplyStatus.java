package org.evolboot.im.domain.group.entity;

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
public enum GroupApplyStatus {

    /**
     * 可自由
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

    private static final Map<Integer, GroupApplyStatus> VALUES = Maps.newHashMapWithExpectedSize(GroupApplyStatus.values().length);

    static {
        Arrays.stream(GroupApplyStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static GroupApplyStatus convertTo(Integer value) {
        return VALUES.get(value);
    }


}

package org.evolboot.im.domain.groupapply.entity;

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

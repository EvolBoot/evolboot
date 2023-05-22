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
public enum GroupType {

    /**
     * 可自由假如
     */
    NORMAL(0),

    ;
    private final Integer value;

    private static final Map<Integer, GroupType> VALUES = Maps.newHashMapWithExpectedSize(GroupType.values().length);

    static {
        Arrays.stream(GroupType.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static GroupType convertTo(Integer value) {
        return VALUES.get(value);
    }


}

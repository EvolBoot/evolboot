package org.evolboot.core.entity;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 */
@Getter
public enum DelState {
    ACTIVE(0),
    ARCHIVE(1);

    private final int value;

    DelState(Integer value) {
        this.value = value;
    }

    private static final Map<Integer, DelState> VALUES = Maps.newHashMapWithExpectedSize(DelState.values().length);

    static {
        Arrays.stream(DelState.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static DelState convertTo(Integer value) {
        return VALUES.get(value);
    }

}

package org.evolboot.core.domain;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 */
@Getter
public enum DelStatus {
    ACTIVE(0),
    ARCHIVE(1);

    private final int value;

    DelStatus(Integer value) {
        this.value = value;
    }

    private static final Map<Integer, DelStatus> VALUES = Maps.newHashMapWithExpectedSize(DelStatus.values().length);

    static {
        Arrays.stream(DelStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static DelStatus convertTo(Integer value) {
        return VALUES.get(value);
    }

}

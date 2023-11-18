package org.evolboot.common.domain.config.entity;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Scope {
    APPLICATION(100);

    private final Integer value;

    private static final Map<Integer, Scope> VALUES = Maps.newHashMapWithExpectedSize(Scope.values().length);

    static {
        Arrays.stream(Scope.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static Scope convertTo(Integer value) {
        return VALUES.get(value);
    }

}

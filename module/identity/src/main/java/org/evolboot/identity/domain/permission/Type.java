package org.evolboot.identity.domain.permission;

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
public enum Type {
    PERM(0),
    MENU(1);

    private final Integer value;

    private static final Map<Integer, Type> VALUES = Maps.newHashMapWithExpectedSize(Type.values().length);

    static {
        Arrays.stream(Type.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static Type convertTo(Integer value) {
        return VALUES.get(value);
    }

}

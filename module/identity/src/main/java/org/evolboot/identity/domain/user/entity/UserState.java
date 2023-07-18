package org.evolboot.identity.domain.user.entity;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 */
@Getter
public enum UserState {

    ACTIVE(0),
    LOCK(1);

    private final Integer value;

    UserState(Integer value) {
        this.value = value;
    }

    private static final Map<Integer, UserState> VALUES = Maps.newHashMap();

    static {
        Arrays.stream(UserState.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static UserState convertTo(Integer value) {
        return VALUES.get(value);
    }


}

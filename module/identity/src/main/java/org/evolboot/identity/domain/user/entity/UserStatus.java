package org.evolboot.identity.domain.user.entity;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 */
@Getter
public enum UserStatus {

    ACTIVE(0),
    LOCK(1);

    private final Integer value;

    UserStatus(Integer value) {
        this.value = value;
    }

    private static final Map<Integer, UserStatus> VALUES = Maps.newHashMap();

    static {
        Arrays.stream(UserStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static UserStatus convertTo(Integer value) {
        return VALUES.get(value);
    }


}

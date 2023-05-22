package org.evolboot.identity.domain.user.entity;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 */
@Getter
public enum UserType {
    NORMAL(0),
    TEST(1);

    private final Integer value;


    private static final Map<Integer, UserType> VALUES = Maps.newHashMap();


    static {
        Arrays.stream(UserType.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    UserType(Integer value) {
        this.value = value;
    }


    public static UserType convertTo(Integer value) {
        return VALUES.get(value);
    }


}


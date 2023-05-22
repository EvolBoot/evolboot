package org.evolboot.system.domain.appupgrade.entity;

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
public enum ClientType {
    ANDROID(0),
    IOS(1),
    GOOGLE(2);


    private final Integer value;

    private static final Map<Integer, ClientType> VALUES = Maps.newHashMapWithExpectedSize(ClientType.values().length);

    static {
        Arrays.stream(ClientType.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static ClientType convertTo(Integer value) {
        return VALUES.get(value);
    }


}

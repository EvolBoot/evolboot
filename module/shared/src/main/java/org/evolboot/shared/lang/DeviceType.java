package org.evolboot.shared.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author corona
 */
@Getter
@AllArgsConstructor
public enum DeviceType {

    IOS(1),
    ANDROID(2),
    WINDOWS(3),
    OSX(4),
    WEB(5),
    MINI_PROGRAM(6),
    LINUX(7),
    UNKNOWN(999);

    private final Integer value;

    private static final Map<Integer, DeviceType> VALUES = new HashMap<>(DeviceType.values().length);

    static {
        Arrays.stream(DeviceType.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static DeviceType convertTo(Integer value) {
        return VALUES.get(value);
    }


}

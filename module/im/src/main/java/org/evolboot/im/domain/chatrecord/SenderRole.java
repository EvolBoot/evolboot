package org.evolboot.im.domain.chatrecord;

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
public enum SenderRole {

    USER(0),
    SYSTEM(1);

    private final Integer value;

    private static final Map<Integer, SenderRole> VALUES = Maps.newHashMapWithExpectedSize(SenderRole.values().length);

    static {
        Arrays.stream(SenderRole.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static SenderRole convertTo(Integer value) {
        return VALUES.get(value);
    }


}

package org.evolboot.shared.email;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 * 
 */
@Getter
@AllArgsConstructor
public enum EmailMessageTag {
    LOGIN(0),
    REGISTER(1),
    CHECK(2);

    private final Integer value;

    private static final Map<Integer, EmailMessageTag> VALUES = Maps.newHashMapWithExpectedSize(EmailMessageTag.values().length);

    static {
        Arrays.stream(EmailMessageTag.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static EmailMessageTag convertTo(Integer value) {
        return VALUES.get(value);
    }

}

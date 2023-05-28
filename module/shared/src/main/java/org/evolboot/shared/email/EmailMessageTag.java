package org.evolboot.shared.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public enum EmailMessageTag {
    LOGIN(0),
    REGISTER(1),
    CHECK(2);

    private final Integer value;

    private static final Map<Integer, EmailMessageTag> VALUES = new HashMap<>(EmailMessageTag.values().length);

    static {
        Arrays.stream(EmailMessageTag.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static EmailMessageTag convertTo(Integer value) {
        return VALUES.get(value);
    }

}

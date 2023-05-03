package org.evolboot.shared.sms;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 *
 */
@AllArgsConstructor
@Getter
public enum SmsMessageTag {
    REGISTER(0),
    LOGIN(1),
    CHECK(2);

    private final Integer value;

    private static final Map<Integer, SmsMessageTag> VALUES = Maps.newHashMapWithExpectedSize(SmsMessageTag.values().length);

    static {
        Arrays.stream(SmsMessageTag.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static SmsMessageTag convertTo(Integer value) {
        return VALUES.get(value);
    }



}

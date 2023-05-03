package org.evolboot.shared.pay;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * 货币
 *
 * @author evol
 */
@Getter
@AllArgsConstructor
public enum Currency {
    INR(356),
    CNY(156),
    TEST(0);

    private final Integer value;

    private static final Map<Integer, Currency> VALUES = Maps.newHashMapWithExpectedSize(Currency.values().length);

    static {
        Arrays.stream(Currency.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static Currency convertTo(Integer value) {
        return VALUES.get(value);
    }


}

package org.evolboot.shared.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author evol
 * @date 2021/12/10
 */
@Getter
@AllArgsConstructor
public enum ReleasedOrderState {
    PENDING(0),
    FAIL(1),
    SUCCESS(2),
    WAIT(3);

    private final Integer value;

    private static final Map<Integer, ReleasedOrderState> VALUES = new HashMap<>(ReleasedOrderState.values().length);

    static {
        Arrays.stream(ReleasedOrderState.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static ReleasedOrderState convertTo(Integer value) {
        return VALUES.get(value);
    }

}

package org.evolboot.shared.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 代付订单状态（Payout）
 */
@Getter
@AllArgsConstructor
public enum PayoutOrderState {
    PENDING(0),
    FAIL(1),
    SUCCESS(2),
    WAIT(3);

    private final Integer value;

    private static final Map<Integer, PayoutOrderState> VALUES = new HashMap<>(PayoutOrderState.values().length);

    static {
        Arrays.stream(PayoutOrderState.values()).forEach(e -> VALUES.put(e.getValue(), e));
    }

    public static PayoutOrderState convertTo(Integer value) {
        return VALUES.get(value);
    }
}

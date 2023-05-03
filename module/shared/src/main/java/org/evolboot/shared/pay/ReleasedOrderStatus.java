package org.evolboot.shared.pay;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 * @date 2021/12/10
 */
@Getter
@AllArgsConstructor
public enum ReleasedOrderStatus {
    PENDING(0),
    FAIL(1),
    SUCCESS(2),
    WAIT(3);

    private final Integer value;

    private static final Map<Integer, ReleasedOrderStatus> VALUES = Maps.newHashMapWithExpectedSize(ReleasedOrderStatus.values().length);

    static {
        Arrays.stream(ReleasedOrderStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static ReleasedOrderStatus convertTo(Integer value) {
        return VALUES.get(value);
    }

    }

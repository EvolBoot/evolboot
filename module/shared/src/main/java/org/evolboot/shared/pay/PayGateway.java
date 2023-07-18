package org.evolboot.shared.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author evol
 * @date 2021/4/17
 */
@AllArgsConstructor
@Getter
public enum PayGateway {

    HUANQIU_PAY(1),
    METAGO(2),
    QART(2);

    private final Integer value;

    private static final Map<Integer, PayGateway> VALUES = new HashMap<>(PayGateway.values().length);

    static {
        Arrays.stream(PayGateway.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static PayGateway convertTo(Integer value) {
        return VALUES.get(value);
    }


}

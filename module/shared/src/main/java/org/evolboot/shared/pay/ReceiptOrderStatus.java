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
@Getter
@AllArgsConstructor
public enum ReceiptOrderStatus {
    PENDING(0),  // 等待
    FAIL(1), // 失败
    SUCCESS(2); // 成功

    private final Integer value;

    private static final Map<Integer, ReceiptOrderStatus> VALUES = new HashMap<>(ReceiptOrderStatus.values().length);

    static {
        Arrays.stream(ReceiptOrderStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static ReceiptOrderStatus convertTo(Integer value) {
        return VALUES.get(value);
    }

}

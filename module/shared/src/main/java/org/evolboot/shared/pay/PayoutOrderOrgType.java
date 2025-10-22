package org.evolboot.shared.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 代付渠道组织类型
 */
@Getter
@AllArgsConstructor
public enum PayoutOrderOrgType {

    IMPS(0),
    BANK(1);

    private final Integer value;

    private static final Map<Integer, PayoutOrderOrgType> VALUES = new HashMap<>(PayoutOrderOrgType.values().length);

    static {
        Arrays.stream(PayoutOrderOrgType.values()).forEach(e -> VALUES.put(e.getValue(), e));
    }

    public static PayoutOrderOrgType convertTo(Integer value) {
        return VALUES.get(value);
    }
}

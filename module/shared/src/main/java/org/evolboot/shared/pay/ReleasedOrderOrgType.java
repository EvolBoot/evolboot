package org.evolboot.shared.pay;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public enum ReleasedOrderOrgType {

    IMPS(0),
    BANK(1);

    private final Integer value;

    private static final Map<Integer, ReleasedOrderOrgType> VALUES = Maps.newHashMapWithExpectedSize(ReleasedOrderOrgType.values().length);

    static {
        Arrays.stream(ReleasedOrderOrgType.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static ReleasedOrderOrgType convertTo(Integer value) {
        return VALUES.get(value);
    }


}

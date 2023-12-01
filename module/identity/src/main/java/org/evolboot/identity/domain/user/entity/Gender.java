
package org.evolboot.identity.domain.user.entity;

import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
@AllArgsConstructor
@Schema(title = "性别")
public enum Gender {
    MALE(1),
    FEMALE(0),
    UNKNOWN(2);

    private final Integer value;

    private static final Map<Integer, Gender> VALUES = Maps.newHashMapWithExpectedSize(Gender.values().length);

    static {
        Arrays.stream(Gender.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static Gender convertTo(Integer value) {
        return VALUES.get(value);
    }

}

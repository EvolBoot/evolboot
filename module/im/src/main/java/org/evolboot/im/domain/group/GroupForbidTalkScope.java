package org.evolboot.im.domain.group;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * 禁言范围
 *
 * @author evol
 */
@Getter
@AllArgsConstructor
public enum GroupForbidTalkScope {

    /**
     * 不禁言
     */
    NONE(0),
    /**
     * 全员
     */
    FULL(1),
    /**
     * 仅会员
     */
    ONLY_MEMBER(2);

    private final Integer value;

    private static final Map<Integer, GroupForbidTalkScope> VALUES = Maps.newHashMapWithExpectedSize(GroupForbidTalkScope.values().length);

    static {
        Arrays.stream(GroupForbidTalkScope.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static GroupForbidTalkScope convertTo(Integer value) {
        return VALUES.get(value);
    }


}

package org.evolboot.im.domain.groupmember.entity;

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
public enum GroupMemberState {

    NORMAL(0),

    FORBID_TALK(1),
    ;
    private final Integer value;

    private static final Map<Integer, GroupMemberState> VALUES = Maps.newHashMapWithExpectedSize(GroupMemberState.values().length);

    static {
        Arrays.stream(GroupMemberState.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static GroupMemberState convertTo(Integer value) {
        return VALUES.get(value);
    }


}

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
public enum GroupMemberStatus {

    NORMAL(0),

    FORBID_TALK(1),
    ;
    private final Integer value;

    private static final Map<Integer, GroupMemberStatus> VALUES = Maps.newHashMapWithExpectedSize(GroupMemberStatus.values().length);

    static {
        Arrays.stream(GroupMemberStatus.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static GroupMemberStatus convertTo(Integer value) {
        return VALUES.get(value);
    }


}

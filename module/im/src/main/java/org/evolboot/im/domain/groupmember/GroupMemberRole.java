package org.evolboot.im.domain.groupmember;

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
public enum GroupMemberRole {

    /**
     * 群主
     */
    OWNER(0),
    /**
     * 管理员
     */
    ADMIN(1),
    /**
     * 一般成员
     */
    GENERAL(3),

    ;
    private final Integer value;

    private static final Map<Integer, GroupMemberRole> VALUES = Maps.newHashMapWithExpectedSize(GroupMemberRole.values().length);

    static {
        Arrays.stream(GroupMemberRole.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }

    public static GroupMemberRole convertTo(Integer value) {
        return VALUES.get(value);
    }


}

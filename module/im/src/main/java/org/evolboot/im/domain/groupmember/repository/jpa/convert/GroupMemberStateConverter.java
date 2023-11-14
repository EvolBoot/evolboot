package org.evolboot.im.domain.groupmember.repository.jpa.convert;


import org.evolboot.im.domain.groupmember.entity.GroupMemberState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class GroupMemberStateConverter implements AttributeConverter<GroupMemberState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GroupMemberState GroupMemberState) {
        return GroupMemberState.getValue();
    }

    @Override
    public GroupMemberState convertToEntityAttribute(Integer value) {
        return GroupMemberState.convertTo(value);
    }

}

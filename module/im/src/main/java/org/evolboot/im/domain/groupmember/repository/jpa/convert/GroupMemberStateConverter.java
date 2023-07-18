package org.evolboot.im.domain.groupmember.repository.jpa.convert;


import org.evolboot.im.domain.groupmember.entity.GroupMemberState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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

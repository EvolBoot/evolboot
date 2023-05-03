package org.evolboot.im.domain.groupmember.repository.jpa.convert;




import org.evolboot.im.domain.groupmember.GroupMemberStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class GroupMemberStatusConverter implements AttributeConverter<GroupMemberStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GroupMemberStatus GroupMemberStatus) {
        return GroupMemberStatus.getValue();
    }

    @Override
    public GroupMemberStatus convertToEntityAttribute(Integer value) {
        return GroupMemberStatus.convertTo(value);
    }

}

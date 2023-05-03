package org.evolboot.im.domain.groupmember.repository.jpa.convert;




import org.evolboot.im.domain.groupmember.GroupMemberRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class GroupMemberRoleConverter implements AttributeConverter<GroupMemberRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GroupMemberRole GroupMemberRole) {
        return GroupMemberRole.getValue();
    }

    @Override
    public GroupMemberRole convertToEntityAttribute(Integer value) {
        return GroupMemberRole.convertTo(value);
    }

}

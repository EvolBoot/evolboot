package org.evolboot.im.domain.group.repository.jpa.convert;



import org.evolboot.im.domain.group.GroupStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class GroupStatusConverter implements AttributeConverter<GroupStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GroupStatus GroupStatus) {
        return GroupStatus.getValue();
    }

    @Override
    public GroupStatus convertToEntityAttribute(Integer value) {
        return GroupStatus.convertTo(value);
    }

}

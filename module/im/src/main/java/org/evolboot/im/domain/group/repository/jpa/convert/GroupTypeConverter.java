package org.evolboot.im.domain.group.repository.jpa.convert;


import org.evolboot.im.domain.group.entity.GroupType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class GroupTypeConverter implements AttributeConverter<GroupType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GroupType GroupType) {
        return GroupType.getValue();
    }

    @Override
    public GroupType convertToEntityAttribute(Integer value) {
        return GroupType.convertTo(value);
    }

}

package org.evolboot.im.domain.group.repository.jpa.convert;



import org.evolboot.im.domain.group.GroupApplyStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class GroupApplyStatusConverter implements AttributeConverter<GroupApplyStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GroupApplyStatus GroupApplyStatus) {
        return GroupApplyStatus.getValue();
    }

    @Override
    public GroupApplyStatus convertToEntityAttribute(Integer value) {
        return GroupApplyStatus.convertTo(value);
    }

}

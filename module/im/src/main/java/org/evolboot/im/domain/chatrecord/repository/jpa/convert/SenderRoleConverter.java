package org.evolboot.im.domain.chatrecord.repository.jpa.convert;


import org.evolboot.im.domain.chatrecord.entity.SenderRole;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class SenderRoleConverter implements AttributeConverter<SenderRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SenderRole SenderRole) {
        return SenderRole.getValue();
    }

    @Override
    public SenderRole convertToEntityAttribute(Integer value) {
        return SenderRole.convertTo(value);
    }

}

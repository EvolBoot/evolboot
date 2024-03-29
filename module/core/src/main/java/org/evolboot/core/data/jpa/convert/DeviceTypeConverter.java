package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.lang.DeviceType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class DeviceTypeConverter implements AttributeConverter<DeviceType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DeviceType attribute) {
        return attribute.getValue();
    }

    @Override
    public DeviceType convertToEntityAttribute(Integer dbData) {
        return DeviceType.convertTo(dbData);
    }

}

package org.evolboot.storage.domain.blob.repository.jpa.convert;


import org.evolboot.storage.domain.blob.entity.StorageType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class StorageTypeConverter implements AttributeConverter<StorageType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StorageType attribute) {
        return attribute.getValue();
    }

    @Override
    public StorageType convertToEntityAttribute(Integer dbData) {
        return StorageType.convertTo(dbData);
    }

}

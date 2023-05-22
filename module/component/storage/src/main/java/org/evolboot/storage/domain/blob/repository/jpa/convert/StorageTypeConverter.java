package org.evolboot.storage.domain.blob.repository.jpa.convert;


import org.evolboot.storage.domain.blob.entity.StorageType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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

package org.evolboot.storage.domain.blob.repository.jpa.convert;


import org.evolboot.storage.domain.blob.entity.BlobType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class BlobTypeConverter implements AttributeConverter<BlobType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BlobType attribute) {
        return attribute.getValue();
    }

    @Override
    public BlobType convertToEntityAttribute(Integer dbData) {
        return BlobType.convertTo(dbData);
    }

}

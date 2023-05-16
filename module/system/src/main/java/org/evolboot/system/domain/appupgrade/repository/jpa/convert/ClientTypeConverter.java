package org.evolboot.system.domain.appupgrade.repository.jpa.convert;


import org.evolboot.system.domain.appupgrade.ClientType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class ClientTypeConverter implements AttributeConverter<ClientType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ClientType attribute) {
        return attribute.getValue();
    }

    @Override
    public ClientType convertToEntityAttribute(Integer dbData) {
        return ClientType.convertTo(dbData);
    }

}

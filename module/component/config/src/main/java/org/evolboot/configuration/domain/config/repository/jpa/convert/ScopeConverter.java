package org.evolboot.configuration.domain.config.repository.jpa.convert;


import org.evolboot.configuration.domain.config.entity.Scope;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class ScopeConverter implements AttributeConverter<Scope, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Scope attribute) {
        return attribute.getValue();
    }

    @Override
    public Scope convertToEntityAttribute(Integer dbData) {
        return Scope.convertTo(dbData);
    }

}

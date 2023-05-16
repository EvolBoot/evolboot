package org.evolboot.configuration.domain.config.repository.jpa;


import org.evolboot.configuration.domain.config.Scope;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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

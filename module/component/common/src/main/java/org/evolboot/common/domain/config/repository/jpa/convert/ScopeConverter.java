package org.evolboot.common.domain.config.repository.jpa.convert;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.evolboot.common.domain.config.entity.Scope;

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

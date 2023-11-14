package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.pay.Currency;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class CurrencyConverter implements AttributeConverter<Currency, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Currency attribute) {
        return attribute.getValue();
    }

    @Override
    public Currency convertToEntityAttribute(Integer dbData) {
        return Currency.convertTo(dbData);
    }

}

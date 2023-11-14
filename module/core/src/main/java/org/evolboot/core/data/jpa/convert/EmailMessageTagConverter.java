package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.email.EmailMessageTag;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class EmailMessageTagConverter implements AttributeConverter<EmailMessageTag, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmailMessageTag attribute) {
        return attribute.getValue();
    }

    @Override
    public EmailMessageTag convertToEntityAttribute(Integer dbData) {
        return EmailMessageTag.convertTo(dbData);
    }

}

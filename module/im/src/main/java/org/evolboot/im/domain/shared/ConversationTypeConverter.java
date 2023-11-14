package org.evolboot.im.domain.shared;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class ConversationTypeConverter implements AttributeConverter<ConversationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ConversationType conversationType) {
        return conversationType.getValue();
    }

    @Override
    public ConversationType convertToEntityAttribute(Integer value) {
        return ConversationType.convertTo(value);
    }

}

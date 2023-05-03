package org.evolboot.im.domain.chatrecord.repository.jpa.convert;


import org.evolboot.im.domain.chatrecord.message.MessageStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class MessageStatusConverter implements AttributeConverter<MessageStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MessageStatus MessageStatus) {
        return MessageStatus.getValue();
    }

    @Override
    public MessageStatus convertToEntityAttribute(Integer value) {
        return MessageStatus.convertTo(value);
    }

}

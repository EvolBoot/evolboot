package org.evolboot.captcha.domain.shared.jpa;


import org.evolboot.shared.sms.SmsMessageTag;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class SmslMessageTagConverter implements AttributeConverter<SmsMessageTag, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SmsMessageTag attribute) {
        return attribute.getValue();
    }

    @Override
    public SmsMessageTag convertToEntityAttribute(Integer dbData) {
        return SmsMessageTag.convertTo(dbData);
    }

}

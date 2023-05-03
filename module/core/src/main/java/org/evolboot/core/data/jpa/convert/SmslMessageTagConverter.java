package org.evolboot.core.data.jpa.convert;




import org.evolboot.shared.sms.SmsMessageTag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class SmslMessageTagConverter implements AttributeConverter<SmsMessageTag,Integer> {

    @Override
    public Integer convertToDatabaseColumn(SmsMessageTag attribute) {
        return attribute.getValue();
    }

    @Override
    public SmsMessageTag convertToEntityAttribute(Integer dbData) {
        return SmsMessageTag.convertTo(dbData);
    }

}

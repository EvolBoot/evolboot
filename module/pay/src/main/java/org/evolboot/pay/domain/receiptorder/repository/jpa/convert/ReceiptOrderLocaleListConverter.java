package org.evolboot.pay.domain.receiptorder.repository.jpa.convert;

import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderLocale;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 */
public class ReceiptOrderLocaleListConverter implements AttributeConverter<List<ReceiptOrderLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<ReceiptOrderLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<ReceiptOrderLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, ReceiptOrderLocale.class);
    }
}

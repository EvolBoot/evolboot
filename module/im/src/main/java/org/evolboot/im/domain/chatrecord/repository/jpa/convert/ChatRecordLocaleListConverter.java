package org.evolboot.im.domain.chatrecord.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.chatrecord.entity.ChatRecordLocale;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * @date 2023-05-03 00:02:35
 */
public class ChatRecordLocaleListConverter implements AttributeConverter<List<ChatRecordLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<ChatRecordLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<ChatRecordLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, ChatRecordLocale.class);
    }
}

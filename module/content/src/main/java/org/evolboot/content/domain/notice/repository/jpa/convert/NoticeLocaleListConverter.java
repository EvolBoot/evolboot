package org.evolboot.content.domain.notice.repository.jpa.convert;

import org.evolboot.content.domain.notice.NoticeLocale;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * 
 */
public class NoticeLocaleListConverter implements AttributeConverter<List<NoticeLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<NoticeLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<NoticeLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, NoticeLocale.class);
    }
}

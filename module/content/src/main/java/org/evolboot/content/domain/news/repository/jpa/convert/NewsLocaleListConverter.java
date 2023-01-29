package org.evolboot.content.domain.news.repository.jpa.convert;

import org.evolboot.content.domain.news.NewsLocale;
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
public class NewsLocaleListConverter implements AttributeConverter<List<NewsLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<NewsLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<NewsLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, NewsLocale.class);
    }
}

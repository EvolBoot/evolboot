package org.evolboot.content.domain.banner.repository.jpa.convert;

import org.evolboot.content.domain.banner.BannerLocale;
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
public class BannerLocaleListConverter implements AttributeConverter<List<BannerLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<BannerLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<BannerLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, BannerLocale.class);
    }
}

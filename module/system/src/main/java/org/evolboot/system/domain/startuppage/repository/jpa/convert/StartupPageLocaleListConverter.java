package org.evolboot.system.domain.startuppage.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.system.domain.startuppage.StartupPageLocale;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * 
 */
public class StartupPageLocaleListConverter implements AttributeConverter<List<StartupPageLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<StartupPageLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<StartupPageLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, StartupPageLocale.class);
    }
}

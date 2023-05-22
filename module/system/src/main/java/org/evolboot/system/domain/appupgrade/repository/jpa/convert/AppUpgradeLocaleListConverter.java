package org.evolboot.system.domain.appupgrade.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.system.domain.appupgrade.entity.AppUpgradeLocale;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 */
public class AppUpgradeLocaleListConverter implements AttributeConverter<List<AppUpgradeLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<AppUpgradeLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<AppUpgradeLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, AppUpgradeLocale.class);
    }
}

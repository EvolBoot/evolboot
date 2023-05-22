package org.evolboot.im.domain.group.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.group.entity.GroupLocale;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * @date 2023-05-03 15:52:47
 */
public class GroupLocaleListConverter implements AttributeConverter<List<GroupLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<GroupLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<GroupLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, GroupLocale.class);
    }
}

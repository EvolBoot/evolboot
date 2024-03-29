package org.evolboot.im.domain.groupapply.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.groupapply.entity.GroupApplyLocale;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * @date 2023-05-03 17:27:04
 */
public class GroupApplyLocaleListConverter implements AttributeConverter<List<GroupApplyLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<GroupApplyLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<GroupApplyLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, GroupApplyLocale.class);
    }
}

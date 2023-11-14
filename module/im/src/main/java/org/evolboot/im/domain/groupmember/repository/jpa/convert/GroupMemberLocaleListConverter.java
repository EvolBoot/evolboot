package org.evolboot.im.domain.groupmember.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.groupmember.entity.GroupMemberLocale;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * @date 2023-05-16 17:06:45
 */
public class GroupMemberLocaleListConverter implements AttributeConverter<List<GroupMemberLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<GroupMemberLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<GroupMemberLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, GroupMemberLocale.class);
    }
}

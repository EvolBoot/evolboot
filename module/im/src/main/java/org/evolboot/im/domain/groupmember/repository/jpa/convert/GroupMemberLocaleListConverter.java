package org.evolboot.im.domain.groupmember.repository.jpa.convert;

import org.evolboot.im.domain.groupmember.GroupMemberLocale;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * @date 2023-05-03 16:20:09
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

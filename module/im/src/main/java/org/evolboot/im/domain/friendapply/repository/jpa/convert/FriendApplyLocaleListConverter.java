package org.evolboot.im.domain.friendapply.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.friendapply.entity.FriendApplyLocale;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * @date 2023-05-03 17:57:08
 */
public class FriendApplyLocaleListConverter implements AttributeConverter<List<FriendApplyLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<FriendApplyLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<FriendApplyLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, FriendApplyLocale.class);
    }
}

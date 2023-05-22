package org.evolboot.im.domain.friend.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.friend.entity.FriendLocale;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * @date 2023-05-03 17:40:14
 */
public class FriendLocaleListConverter implements AttributeConverter<List<FriendLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<FriendLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<FriendLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, FriendLocale.class);
    }
}

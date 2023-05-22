package org.evolboot.im.domain.group.repository.jpa.convert;


import org.evolboot.im.domain.group.entity.GroupForbidTalkScope;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class GroupForbidScopeTalkConverter implements AttributeConverter<GroupForbidTalkScope, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GroupForbidTalkScope GroupForbidTalkScope) {
        return GroupForbidTalkScope.getValue();
    }

    @Override
    public GroupForbidTalkScope convertToEntityAttribute(Integer value) {
        return GroupForbidTalkScope.convertTo(value);
    }

}

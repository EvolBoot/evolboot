package org.evolboot.im.domain.friendapply.repository.jpa.convert;


import org.evolboot.im.domain.friendapply.FriendApplyStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class FriendApplyStatusConverter implements AttributeConverter<FriendApplyStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FriendApplyStatus FriendApplyStatus) {
        return FriendApplyStatus.getValue();
    }

    @Override
    public FriendApplyStatus convertToEntityAttribute(Integer value) {
        return FriendApplyStatus.convertTo(value);
    }

}

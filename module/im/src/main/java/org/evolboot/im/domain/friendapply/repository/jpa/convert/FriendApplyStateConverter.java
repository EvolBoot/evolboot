package org.evolboot.im.domain.friendapply.repository.jpa.convert;


import org.evolboot.im.domain.friendapply.entity.FriendApplyState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class FriendApplyStateConverter implements AttributeConverter<FriendApplyState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FriendApplyState FriendApplyState) {
        return FriendApplyState.getValue();
    }

    @Override
    public FriendApplyState convertToEntityAttribute(Integer value) {
        return FriendApplyState.convertTo(value);
    }

}

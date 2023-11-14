package org.evolboot.im.domain.friend.repository.jpa.convert;


import org.evolboot.im.domain.friend.entity.FriendState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class FriendStateConverter implements AttributeConverter<FriendState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FriendState friendState) {
        return friendState.getValue();
    }

    @Override
    public FriendState convertToEntityAttribute(Integer value) {
        return FriendState.convertTo(value);
    }

}

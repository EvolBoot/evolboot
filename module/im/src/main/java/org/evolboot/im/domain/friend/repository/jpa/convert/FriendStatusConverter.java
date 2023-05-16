package org.evolboot.im.domain.friend.repository.jpa.convert;


import org.evolboot.im.domain.friend.FriendStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class FriendStatusConverter implements AttributeConverter<FriendStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FriendStatus FriendStatus) {
        return FriendStatus.getValue();
    }

    @Override
    public FriendStatus convertToEntityAttribute(Integer value) {
        return FriendStatus.convertTo(value);
    }

}

package org.evolboot.im.domain.conversation.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.shared.ConversationType;

import java.util.Date;


/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Setter
@Getter
public class ConversationQuery extends Query {

    private Long id;

    private Date startDate;

    private Date endDate;

    private ConversationType type;

    @Builder
    public ConversationQuery(Long id, Integer page, Integer limit, Date startDate, Date endDate, ConversationType type, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.id = id;
        this.type = type;
        if (ExtendObjects.nonNull(startDate)) {
            this.startDate = ExtendDateUtil.beginOfDay(startDate);
        }
        if (ExtendObjects.nonNull(endDate)) {
            this.endDate = ExtendDateUtil.endOfDay(endDate);
        }
    }
}

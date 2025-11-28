package org.evolboot.im.domain.conversation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.shared.ConversationType;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Setter
@Getter
public class ConversationQueryRequest extends Query {

    private Long id;

    private LocalDateTime beginAt;

    private LocalDateTime endAt;

    private ConversationType type;

    @Builder
    public ConversationQueryRequest(Long id, Integer page, Integer limit, LocalDateTime beginAt, LocalDateTime endAt, ConversationType type, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
        this.id = id;
        this.type = type;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }
}

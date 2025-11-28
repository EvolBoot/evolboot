package org.evolboot.im.domain.chatrecord.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Setter
@Getter
public class ChatRecordQueryRequest extends Query {

    private Long id;

    private LocalDateTime beginAt;

    private LocalDateTime endAt;

    @Builder
    public ChatRecordQueryRequest(Long id, Integer page, Integer limit, LocalDateTime beginAt, LocalDateTime endAt, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
        this.id = id;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }
}

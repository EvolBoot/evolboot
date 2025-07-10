package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto;

import projectPackage.core.data.Query;
import projectPackage.core.data.Sort;
import projectPackage.core.data.Direction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import projectPackage.core.util.ExtendDateUtil;
import projectPackage.core.util.ExtendObjects;

import java.util.Date;


/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Setter
@Getter
public class XarvkgvvrllncQueryRequest extends Query {

    private Keya2Akk5iV3n id;

    private Date beginAt;

    private Date endAt;

    private String keyword;

    private Long userId;

    @Builder
    public XarvkgvvrllncQueryRequest(Keya2Akk5iV3n id, Integer page, Integer limit, Date beginAt, Date endAt, String keyword, String orderField, Direction order,Long userId) {
        super(page, limit, orderField, order);
        this.id = id;
        this.keyword = keyword;
        this.userId = userId;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }
}

package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc;

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
public class XarvkgvvrllncQuery extends Query {

    private Keya2Akk5iV3n id;

    private Date startDate;

    private Date endDate;

    private String keyword;

    @Builder
    public XarvkgvvrllncQuery(Keya2Akk5iV3n id, Integer page, Integer limit, Date startDate, Date endDate, String keyword, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.id = id;
        this.keyword = keyword;
        if (ExtendObjects.nonNull(startDate)) {
            this.startDate = ExtendDateUtil.beginOfDay(startDate);
        }
        if (ExtendObjects.nonNull(endDate)) {
            this.endDate = ExtendDateUtil.endOfDay(endDate);
        }
    }
}

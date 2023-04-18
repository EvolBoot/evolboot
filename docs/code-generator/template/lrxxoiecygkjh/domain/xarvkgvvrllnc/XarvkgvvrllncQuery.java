package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc;

import projectPackage.core.data.Query;
import projectPackage.core.data.Sort;
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

    @Builder
    public XarvkgvvrllncQuery(Keya2Akk5iV3n id, Integer page, Integer limit, Date startDate, Date endDate) {
        super(page, limit);
        this.id = id;
        if (ExtendObjects.nonNull(startDate)) {
            this.startDate = ExtendDateUtil.beginOfDay(startDate);
        }
        if (ExtendObjects.nonNull(endDate)) {
            this.endDate = ExtendDateUtil.endOfDay(endDate);
        }
    }
}

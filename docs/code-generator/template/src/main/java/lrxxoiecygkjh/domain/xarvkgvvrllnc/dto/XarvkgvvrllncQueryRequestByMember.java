package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto;

import projectPackage.core.data.Query;
import projectPackage.core.data.Sort;
import projectPackage.core.data.Direction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import projectPackage.core.util.ExtendDateUtil;
import projectPackage.core.util.ExtendObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;


/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Setter
@Getter
public class XarvkgvvrllncQueryRequestByMember {


    @Schema(title = "页数")
    private Integer page = 1;

    @Schema(title = "每页数量")
    private Integer limit;

    @Schema(title = "ids")
    private List<Keya2Akk5iV3n> ids;

    @Schema(title = "id")
    private Keya2Akk5iV3n id;

    @Schema(title = "开始时间")
    private Date beginAt;

    @Schema(title = "结束时间")
    private Date endAt;

    @Schema(title = "关键字")
    private String keyword;

    public XarvkgvvrllncQueryRequest convert(Long userId) {
        return XarvkgvvrllncQueryRequest
                .builder()
                .page(page)
                .limit(limit)
                .userId(userId)
                .ids(ids)
                .keyword(keyword)
                .beginAt(beginAt)
                .endAt(endAt)
                .build();
    }




}

package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto;

import projectPackage.core.data.Query;
import projectPackage.core.data.Sort;
import projectPackage.core.data.Direction;
import projectPackage.shared.lang.CurrentPrincipal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import projectPackage.core.util.ExtendDateUtil;
import projectPackage.core.util.ExtendObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 模板批量查询请求
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Setter
@Getter
public class XarvkgvvrllncBatchQueryRequest {


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

    /**
     * 转换为标准查询请求
     */
    public XarvkgvvrllncQueryRequest convert(Long userId, Long tenantId) {
        return XarvkgvvrllncQueryRequest
                .builder()
                .page(page)
                .limit(limit)
                .userId(userId)
                .tenantId(tenantId)
                .ids(ids)
                .keyword(keyword)
                .beginAt(beginAt)
                .endAt(endAt)
                .build();
    }

    /**
     * 使用 CurrentPrincipal 转换为标准查询请求
     */
    public XarvkgvvrllncQueryRequest convert(CurrentPrincipal currentPrincipal) {
        if (ExtendObjects.isNull(currentPrincipal)) {
            return convert(null, null);
        }
        return convert(currentPrincipal.getUserId(), currentPrincipal.getTenantId());
    }


}

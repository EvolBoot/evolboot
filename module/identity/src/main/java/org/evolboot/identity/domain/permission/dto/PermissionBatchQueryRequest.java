package org.evolboot.identity.domain.permission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.permission.entity.PermissionScope;
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.shared.lang.CurrentPrincipal;

import java.util.Collection;

/**
 * 权限批量查询请求
 *
 * @author evol
 */
@Setter
@Getter
public class PermissionBatchQueryRequest {

    @Schema(title = "页数")
    private Integer page = 1;

    @Schema(title = "每页数量")
    private Integer limit = 10;

    @Schema(title = "权限标题")
    private String title;

    @Schema(title = "权限代码")
    private String code;

    @Schema(title = "父级权限ID")
    private Long parentId;

    @Schema(title = "权限类型")
    private Type type;

    @Schema(title = "权限ID集合")
    private Collection<Long> ids;

    @Schema(title = "权限范围")
    private PermissionScope scope;

    @Schema(title = "排序字段")
    private String sortField;

    @Schema(title = "排序方向")
    private Direction direction;

    /**
     * 转换为标准查询请求
     */
    public PermissionQueryRequest convert() {
        return PermissionQueryRequest.builder()
                .page(page)
                .limit(limit)
                .title(title)
                .code(code)
                .parentId(parentId)
                .type(type)
                .ids(ids)
                .scope(scope)
                .sortField(sortField)
                .direction(direction)
                .build();
    }

    /**
     * 转换为标准查询请求，强制指定 scope
     */
    public PermissionQueryRequest convert(PermissionScope scope) {
        return PermissionQueryRequest.builder()
                .page(page)
                .limit(limit)
                .title(title)
                .code(code)
                .parentId(parentId)
                .type(type)
                .ids(ids)
                .scope(scope)
                .sortField(sortField)
                .direction(direction)
                .build();
    }
}

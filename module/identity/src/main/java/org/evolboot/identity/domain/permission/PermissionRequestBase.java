package org.evolboot.identity.domain.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Setter
@Getter
public class PermissionRequestBase {


    @Schema(description = "标题", example = "123456")
    private String title;

    @Schema(description = "权限", example = "123456")
    private String perm;

    @Schema(description = "对应的PATH", example = "123456")
    private String path;

    @Schema(description = "类型", example = "PERM")
    private Type type;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "菜单上的Icon", example = "eye")
    private String icon;

    @Schema(description = "备注", example = "备注")
    private String remark;
}

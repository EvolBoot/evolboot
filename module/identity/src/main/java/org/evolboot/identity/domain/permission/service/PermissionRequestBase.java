package org.evolboot.identity.domain.permission.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.identity.domain.permission.entity.Meta;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.Type;

import java.util.List;

/**
 * @author evol
 */
@Setter
@Getter
public class PermissionRequestBase {

    @Schema(description = "上级", example = "[1]")
    private List<Long> parentIds;

    @Schema(description = "name", example = "name")
    private String name;

    @Schema(description = "对应的PATH", example = "123456")
    private String path;

    @Schema(description = "类型", example = "menu")
    private Type type;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "是否链接", example = "false")
    private Boolean isLink;

    @Schema(description = "组件", example = "/home")
    private String component;

    private Meta meta;


}

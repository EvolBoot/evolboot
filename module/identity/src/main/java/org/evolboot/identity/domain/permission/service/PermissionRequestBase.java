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

    @Schema(title = "上级", example = "[1]")
    private List<Long> parentIds;

    @Schema(title = "name", example = "name")
    private String name;

    @Schema(title = "对应的PATH", example = "123456")
    private String path;

    @Schema(title = "类型", example = "menu")
    private Type type;

    @Schema(title = "排序", example = "1")
    private Integer sort;

    @Schema(title = "是否链接", example = "false")
    private Boolean isLink;

    @Schema(title = "组件", example = "/home")
    private String component;

    private Meta meta;


}

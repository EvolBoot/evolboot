package org.evolboot.identity.remote.permission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author evol
 */
public abstract class PermissionRequestSupport {

    @Data
    public static class UIComponentRequest {
        @Schema(title = "菜单上显示的名称-好像没啥用", example = "名称")
        String uiName;

        @Schema(title = "菜单上的路由", example = "/test")
        String uiRoute;

        @Schema(title = "菜单上的Icon", example = "eye")
        String uiIcon;
    }
}

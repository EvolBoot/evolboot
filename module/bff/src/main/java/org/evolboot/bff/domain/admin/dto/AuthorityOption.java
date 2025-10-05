package org.evolboot.bff.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限选项数据模型
 * @author evol
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityOption {

    /**
     * 权限码，如: identity:user:create
     */
    private String perm;

    /**
     * 权限标题/描述
     */
    private String title;

    /**
     * 对应的URL路径
     */
    private String url;

    /**
     * 模块标识，如: identity
     */
    private String module;

    /**
     * 模块中文名，如: 身份管理
     */
    private String moduleLabel;

    /**
     * 资源标识，如: user
     */
    private String resource;

    /**
     * 资源中文名，如: 用户
     */
    private String resourceLabel;

    /**
     * 操作名，如: create
     */
    private String action;
}

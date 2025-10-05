package org.evolboot.bff.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 权限树形结构数据模型
 * @author evol
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityTree {

    /**
     * 节点显示标签
     */
    private String label;

    /**
     * 节点值
     */
    private String value;

    /**
     * 子节点列表
     */
    private List<AuthorityTree> children;

    /**
     * 叶子节点：权限码
     */
    private String perm;

    /**
     * 叶子节点：对应的URL
     */
    private String url;

    /**
     * 是否为叶子节点
     */
    private Boolean isLeaf;
}

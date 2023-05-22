package org.evolboot.identity.remote.permission;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.Type;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Setter
@Getter
public class PermissionFlatResponse {
    private Long id;

    private Long parentId;

    private String title;

    private String perm;

    private String url;

    private Type type;

    private Integer sort;

    private String icon;

    private String category;

    private String remark;

    public static PermissionFlatResponse of(Permission row) {
        PermissionFlatResponse response = new PermissionFlatResponse();
        BeanUtils.copyProperties(row, response);
        return response;
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<PermissionFlatResponse> of(Page<Permission> page) {
        return PageImpl.<PermissionFlatResponse>builder()
                .page(page.getPage())
                .limit(page.getLimit())
                .totalSize(page.getTotalSize())
                .list(of(page.getList()))
                .build();
    }

    /**
     * 列表
     *
     * @param list
     * @return
     */
    public static List<PermissionFlatResponse> of(List<Permission> list) {
        return list.stream().map(PermissionFlatResponse::of).collect(Collectors.toList());
    }

}

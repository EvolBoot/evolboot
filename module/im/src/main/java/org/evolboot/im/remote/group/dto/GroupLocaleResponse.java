package org.evolboot.im.remote.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.group.entity.Group;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupLocaleResponse {


    private Long id;

    public static GroupLocaleResponse of(Group row) {
//        GroupLocale locale = row.findLocaleByCurrentLanguage(GroupLocale.class);

        return new GroupLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<GroupLocaleResponse> of(Page<Group> page) {
        return PageImpl.<GroupLocaleResponse>builder()
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
    public static List<GroupLocaleResponse> of(List<Group> list) {
        return list.stream().map(GroupLocaleResponse::of).collect(Collectors.toList());
    }

}

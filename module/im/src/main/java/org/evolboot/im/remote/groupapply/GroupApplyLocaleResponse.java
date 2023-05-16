package org.evolboot.im.remote.groupapply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.groupapply.GroupApply;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupApplyLocaleResponse {


    private Long id;

    public static GroupApplyLocaleResponse of(GroupApply row) {
//        GroupApplyLocale locale = row.findLocaleByCurrentLanguage(GroupApplyLocale.class);

        return new GroupApplyLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<GroupApplyLocaleResponse> of(Page<GroupApply> page) {
        return PageImpl.<GroupApplyLocaleResponse>builder()
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
    public static List<GroupApplyLocaleResponse> of(List<GroupApply> list) {
        return list.stream().map(GroupApplyLocaleResponse::of).collect(Collectors.toList());
    }

}

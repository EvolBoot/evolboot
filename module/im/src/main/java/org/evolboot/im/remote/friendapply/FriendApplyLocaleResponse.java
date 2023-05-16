package org.evolboot.im.remote.friendapply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.friendapply.FriendApply;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendApplyLocaleResponse {


    private Long id;

    public static FriendApplyLocaleResponse of(FriendApply row) {
//        FriendApplyLocale locale = row.findLocaleByCurrentLanguage(FriendApplyLocale.class);

        return new FriendApplyLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<FriendApplyLocaleResponse> of(Page<FriendApply> page) {
        return PageImpl.<FriendApplyLocaleResponse>builder()
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
    public static List<FriendApplyLocaleResponse> of(List<FriendApply> list) {
        return list.stream().map(FriendApplyLocaleResponse::of).collect(Collectors.toList());
    }

}

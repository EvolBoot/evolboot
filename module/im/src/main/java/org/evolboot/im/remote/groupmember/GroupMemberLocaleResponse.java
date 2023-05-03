package org.evolboot.im.remote.groupmember;

import org.evolboot.im.domain.groupmember.GroupMember;
import org.evolboot.im.domain.groupmember.GroupMemberLocale;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberLocaleResponse {


    private Long id;

    public static GroupMemberLocaleResponse of(GroupMember row) {
//        GroupMemberLocale locale = row.findLocaleByCurrentLanguage(GroupMemberLocale.class);

        return new GroupMemberLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<GroupMemberLocaleResponse> of(Page<GroupMember> page) {
        return PageImpl.<GroupMemberLocaleResponse>builder()
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
    public static List<GroupMemberLocaleResponse> of(List<GroupMember> list) {
        return list.stream().map(GroupMemberLocaleResponse::of).collect(Collectors.toList());
    }

}

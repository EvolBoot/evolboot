package org.evolboot.im.remote.userconversation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.userconversation.entity.UserConversation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserConversationLocaleResponse {


    private Long id;

    public static UserConversationLocaleResponse of(UserConversation row) {
//        UserConversationLocale locale = row.findLocaleByCurrentLanguage(UserConversationLocale.class);

        return new UserConversationLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<UserConversationLocaleResponse> of(Page<UserConversation> page) {
        return PageImpl.<UserConversationLocaleResponse>builder()
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
    public static List<UserConversationLocaleResponse> of(List<UserConversation> list) {
        return list.stream().map(UserConversationLocaleResponse::of).collect(Collectors.toList());
    }

}

package org.evolboot.im.remote.conversation;

import org.evolboot.im.domain.conversation.Conversation;
import org.evolboot.im.domain.conversation.ConversationLocale;

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
 * @date 2023-05-02 21:43:03
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationLocaleResponse {


    private Long id;

    public static ConversationLocaleResponse of(Conversation row) {
//        ConversationLocale locale = row.findLocaleByCurrentLanguage(ConversationLocale.class);

        return new ConversationLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<ConversationLocaleResponse> of(Page<Conversation> page) {
        return PageImpl.<ConversationLocaleResponse>builder()
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
    public static List<ConversationLocaleResponse> of(List<Conversation> list) {
        return list.stream().map(ConversationLocaleResponse::of).collect(Collectors.toList());
    }

}

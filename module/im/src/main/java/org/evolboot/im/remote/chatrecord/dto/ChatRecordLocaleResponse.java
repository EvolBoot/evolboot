package org.evolboot.im.remote.chatrecord.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRecordLocaleResponse {


    private Long id;

    public static ChatRecordLocaleResponse of(ChatRecord row) {
//        ChatRecordLocale locale = row.findLocaleByCurrentLanguage(ChatRecordLocale.class);

        return new ChatRecordLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<ChatRecordLocaleResponse> of(Page<ChatRecord> page) {
        return PageImpl.<ChatRecordLocaleResponse>builder()
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
    public static List<ChatRecordLocaleResponse> of(List<ChatRecord> list) {
        return list.stream().map(ChatRecordLocaleResponse::of).collect(Collectors.toList());
    }

}

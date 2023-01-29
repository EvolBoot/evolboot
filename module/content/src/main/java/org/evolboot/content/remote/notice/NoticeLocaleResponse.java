package org.evolboot.content.remote.notice;

import org.evolboot.content.domain.notice.Notice;
import org.evolboot.content.domain.notice.NoticeLocale;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeLocaleResponse {


    private Long id;

    private Date releasedTime;

    private NoticeLocale locale;

    public static NoticeLocaleResponse of(Notice row) {
        NoticeLocale locale = row.findLocaleByCurrentLanguage(NoticeLocale.class);
        return new NoticeLocaleResponse(row.id(), row.getReleasedTime(), locale);
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<NoticeLocaleResponse> of(Page<Notice> page) {
        return PageImpl.<NoticeLocaleResponse>builder()
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
    public static List<NoticeLocaleResponse> of(List<Notice> list) {
        return list.stream().map(NoticeLocaleResponse::of).collect(Collectors.toList());
    }

}

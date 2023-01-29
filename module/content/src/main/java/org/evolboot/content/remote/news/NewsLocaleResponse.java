package org.evolboot.content.remote.news;

import org.evolboot.content.domain.news.News;
import org.evolboot.content.domain.news.NewsLocale;
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
public class NewsLocaleResponse {


    private Long id;

    private String source;

    private Date releasedTime;

    private NewsLocale locale;


    public static NewsLocaleResponse of(News row) {
        NewsLocale locale = row.findLocaleByCurrentLanguage(NewsLocale.class);

        return new NewsLocaleResponse(row.id(), row.getSource(), row.getReleasedTime(), locale);
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<NewsLocaleResponse> of(Page<News> page) {
        return PageImpl.<NewsLocaleResponse>builder()
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
    public static List<NewsLocaleResponse> of(List<News> list) {
        return list.stream().map(NewsLocaleResponse::of).collect(Collectors.toList());
    }

}

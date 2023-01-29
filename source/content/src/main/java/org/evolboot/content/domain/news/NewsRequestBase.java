package org.evolboot.content.domain.news;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 新闻
 *
 * @author evol
 */
@Setter
@Getter
public abstract class NewsRequestBase {

    private Integer sort;

    private String source;

    private Date releasedTime;

    private Boolean show;

    private List<NewsLocale> locales;

}

package org.evolboot.content.domain.news;

import org.evolboot.content.domain.news.repository.jpa.convert.NewsLocaleListConverter;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.domain.LocaleDomainPart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


/**
 * 新闻
 *
 * @author evol
 * 
 */

@Entity
@Table(name = "evol_content_news")
@Getter
@Slf4j
@NoArgsConstructor
public class News extends JpaAbstractEntity<Long> implements AggregateRoot<News>, LocaleDomainPart<NewsLocale> {

    @Id
    private Long id;

    private Integer sort = 0;

    private String source;

    private Date releasedTime;

    private Boolean show = true;

    @Convert(converter = NewsLocaleListConverter.class)
    private List<NewsLocale> locales;

    void setSort(Integer sort) {
        this.sort = sort;
    }

    void setReleasedTime(Date releasedTime) {
        this.releasedTime = releasedTime;
    }

    void setShow(Boolean show) {
        this.show = show;
    }

    void setLocales(List<NewsLocale> locales) {
        this.locales = locales;
    }

    void setSource(String source) {
        this.source = source;
    }

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public News(Integer sort, String source, Date releasedTime, Boolean show, List<NewsLocale> locales) {
        generateId();
        setSort(sort);
        setSource(source);
        setReleasedTime(releasedTime);
        setShow(show);
        setLocales(locales);
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public News root() {
        return this;
    }
}

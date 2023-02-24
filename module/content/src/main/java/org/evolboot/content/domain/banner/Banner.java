package org.evolboot.content.domain.banner;

import org.evolboot.content.domain.banner.repository.jpa.convert.BannerLocaleListConverter;
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
import java.util.List;


/**
 * banner
 *
 * @author evol
 * 
 */
@Table(name = "evoltb_content_banner")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class Banner extends JpaAbstractEntity<Long> implements AggregateRoot<Banner>, LocaleDomainPart<BannerLocale> {

    @Id
    private Long id;

    private Integer sort = 0;

    private Boolean show = true;

    @Convert(converter = BannerLocaleListConverter.class)
    private List<BannerLocale> locales;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    void setSort(Integer sort) {
        this.sort = sort;
    }

    void setShow(Boolean show) {
        this.show = show;
    }

    void setLocales(List<BannerLocale> locales) {
        this.locales = locales;
    }

    public Banner(Integer sort, Boolean show, List<BannerLocale> locales) {
        generateId();
        setSort(sort);
        setShow(show);
        setLocales(locales);
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public Banner root() {
        return this;
    }
}

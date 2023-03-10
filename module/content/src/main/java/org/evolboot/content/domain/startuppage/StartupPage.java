package org.evolboot.content.domain.startuppage;

import org.evolboot.content.domain.startuppage.repository.jpa.convert.StartupPageLocaleListConverter;
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
 * 启动页
 *
 * @author evol
 * 
 */
@Table(name = "evoltb_content_startup_page")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class StartupPage extends JpaAbstractEntity<Long> implements AggregateRoot<StartupPage>, LocaleDomainPart<StartupPageLocale> {

    @Id
    private Long id;

    private Integer sort = 0;

    private Boolean enable = true;

    @Convert(converter = StartupPageLocaleListConverter.class)
    private List<StartupPageLocale> locales;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setEnable(Boolean show) {
        this.enable = show;
    }

    public void setLocales(List<StartupPageLocale> locales) {
        this.locales = locales;
    }

    public StartupPage(Integer sort, Boolean enable, List<StartupPageLocale> locales) {
        generateId();
        setSort(sort);
        setEnable(enable);
        setLocales(locales);
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public StartupPage root() {
        return this;
    }
}

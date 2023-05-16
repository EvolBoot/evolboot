package org.evolboot.system.domain.qa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.domain.LocaleDomainPart;
import org.evolboot.system.domain.qa.repository.jpa.convert.QaLocaleListConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


/**
 * QA
 *
 * @author evol
 */
@Table(name = "evoltb_system_qa")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class Qa extends JpaAbstractEntity<Long> implements AggregateRoot<Qa>, LocaleDomainPart<QaLocale> {

    @Id
    private Long id;

    @Convert(converter = QaLocaleListConverter.class)
    private List<QaLocale> locales;

    private Boolean enable = true;

    private Integer sort;

    private String link;

    private void generateId() {
        this.id = IdGenerate.longId();
    }


    public Qa(List<QaLocale> locales, Boolean enable, Integer sort, String link) {
        generateId();
        setLink(link);
        setLocales(locales);
        setSort(sort);
        setEnable(enable);
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setLocales(List<QaLocale> locales) {
        this.locales = locales;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Qa root() {
        return this;
    }
}

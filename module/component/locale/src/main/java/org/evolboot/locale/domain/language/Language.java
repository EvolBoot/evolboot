package org.evolboot.locale.domain.language;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author evol
 */
@Table(name = "evol_locale_language")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class Language extends JpaAbstractEntity<Long> implements AggregateRoot<Language>, Serializable {

    @Id
    private Long id;

    private String language;
    private String remark;
    private Integer sort = 0;
    private Boolean enable = true;

    void setLanguage(String language) {
        this.language = language;
    }

    void setRemark(String remark) {
        this.remark = remark;
    }

    void setSort(Integer sort) {
        this.sort = sort;
    }

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public Language(String remark, String language, Integer sort) {
        generateId();
        setRemark(remark);
        setLanguage(language);
        setSort(sort);
    }

    public void update(String remark, String language, Integer sort) {
        setLanguage(language);
        setRemark(remark);
        setSort(sort);
    }

    public void enable() {
        this.enable = true;
    }

    public void disable() {
        this.enable = false;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Language root() {
        return this;
    }
}

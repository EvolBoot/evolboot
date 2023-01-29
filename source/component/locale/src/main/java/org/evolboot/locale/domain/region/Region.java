package org.evolboot.locale.domain.region;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author evol
 */
@Table(name = "evol_locale_region")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@ToString
public class Region extends JpaAbstractEntity<Long> implements AggregateRoot<Region>, Serializable {

    @Id
    private Long id;

    private String name = "";
    private String shortName = "";
    private String mobilePrefix = "";
    private String flag = "";
    private String remark = "";
    private Integer sort = 0;

    private Boolean enable = true;

    void setName(String name) {
        this.name = name;
    }

    void setShortName(String shortName) {
        this.shortName = shortName;
    }

    void setMobilePrefix(String mobilePrefix) {
        this.mobilePrefix = mobilePrefix;
    }

    void setFlag(String flag) {
        this.flag = flag;
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

    void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void enable() {
        setEnable(true);
    }

    public void disable() {
        setEnable(false);
    }

    public Region(String name, String shortName, String mobilePrefix, String flag, String remark, Integer sort, Boolean enable) {
        generateId();
        setName(name);
        setShortName(shortName);
        setMobilePrefix(mobilePrefix);
        setFlag(flag);
        setRemark(remark);
        setSort(sort);
        setEnable(enable);
    }

    public void update(String name, String shortName, String mobilePrefix, String flag, String remark, Integer sort, Boolean enable) {
        setName(name);
        setShortName(shortName);
        setMobilePrefix(mobilePrefix);
        setFlag(flag);
        setRemark(remark);
        setSort(sort);
        setEnable(enable);
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public Region root() {
        return this;
    }
}

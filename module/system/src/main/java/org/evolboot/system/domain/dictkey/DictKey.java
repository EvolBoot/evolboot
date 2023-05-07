package org.evolboot.system.domain.dictkey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.Assert;
import org.evolboot.system.domain.dictkey.repository.jpa.convert.DictKeyLocaleListConverter;
import org.evolboot.core.domain.LocaleDomainPart;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;
import java.util.List;


/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Table(name = "evol_system_dict_key")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class DictKey extends JpaAbstractEntity<Long> implements AggregateRoot<DictKey> {

    @Id
    private Long id;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    private String displayName;

    private String key;

    private Integer sort;

    private String remark;


    public DictKey(String displayName, String key, Integer sort, String remark) {
        generateId();
        this.setDisplayName(displayName);
        this.setKey(key);
        this.setSort(sort);
        this.setRemark(remark);
    }

    public void setDisplayName(String displayName) {
        //TODO 多语言
        Assert.notEmpty(displayName, "displayName 不能为空");
        this.displayName = displayName;
    }

    public void setKey(String key) {
        //TODO 多语言
        Assert.notEmpty(key, "key 不能为空");
        this.key = key;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public DictKey root() {
        return this;
    }
}

package org.evolboot.system.domain.dictkey.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Table(name = "evoltb_system_dict_key")
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

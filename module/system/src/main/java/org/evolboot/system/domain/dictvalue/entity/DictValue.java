package org.evolboot.system.domain.dictvalue.entity;

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
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Table(name = "evol_system_dict_value")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class DictValue extends JpaAbstractEntity<Long> implements AggregateRoot<DictValue> {

    @Id
    private Long id;

    private Long dictKeyId;

    private String displayName;

    private String value;

    private Integer sort;

    private String remark;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public void setDictKeyId(Long dictKeyId) {
        Assert.notNull(dictKeyId, "dictKeyId 不能为空");
        this.dictKeyId = dictKeyId;
    }

    public void setDisplayName(String displayName) {
        Assert.notEmpty(displayName, "displayName 不能为空");
        this.displayName = displayName;
    }

    public void setValue(String value) {
        Assert.notEmpty(value, "Value 不能为空");
        this.value = value;
    }

    public void setSort(Integer sort) {
        if (sort == null) {
            sort = 0;
        }
        this.sort = sort;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public DictValue(Long dictKeyId, String displayName, String value, Integer sort, String remark) {
        generateId();
        setDictKeyId(dictKeyId);
        this.setDisplayName(displayName);
        this.setValue(value);
        this.setSort(sort);
        this.setRemark(remark);
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public DictValue root() {
        return this;
    }
}

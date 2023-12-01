package org.evolboot.common.domain.dictvalue.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.util.Assert;


/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Table(name = "evoltb_common_dict_value")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@Schema(title = "字典值")
public class DictValue extends JpaAbstractEntity<Long> implements AggregateRoot<DictValue> {

    @Id
    private Long id;

    @Schema(title = "字典Key类型")
    private Long dictKeyId;

    @Schema(title = "显示名称")
    private String displayName;

    @Schema(title = "字典值")
    private String value;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "备注")
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

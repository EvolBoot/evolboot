package org.evolboot.common.domain.dictkey.entity;

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
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Table(name = "evoltb_common_dict_key")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@Schema(title = "字典Key")
public class DictKey extends JpaAbstractEntity<Long> implements AggregateRoot<DictKey> {

    @Id
    private Long id;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    @Schema(title = "显示名称")
    private String displayName;

    @Schema(title = "Key值")
    private String key;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "备注")
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

package org.evolboot.identity.domain.permission.entity;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.data.jpa.convert.LongListConverter;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;

import jakarta.persistence.*;
import java.util.List;

/**
 * @author evol
 */
@Entity
@Table(name = "evoltb_identity_permission")
@Getter
@Slf4j
@NoArgsConstructor
@Setter
//TODO 多语言
public class Permission extends JpaAbstractEntity<Long> implements AggregateRoot<Permission> {

    public static final long DEFAULT_PERMISSION_PARENT = 0L;

    public static final String SEPARATOR = ";";

    @Id
    private Long id;

    /**
     * 以这个为主
     */
    private Long parentId;

    /**
     * 只是为了回显
     */
    @Convert(converter = LongListConverter.class)
    private List<Long> parentIds;

    private String name;

    private String component;

    private String path;

    private Type type = Type.menu;

    private Integer sort = 0;

    private Boolean isLink = false;

    @Embedded
    private Meta meta;

    @Transient
    private List<Permission> children;

    @Builder
    public Permission(
            List<Long> parentIds,
            String name,
            String component,
            String path,
            Type type,
            Integer sort,
            Boolean isLink,
            Meta meta) {
        generateId();
        setParentIds(parentIds);
        setName(name);
        setComponent(component);
        setPath(path);
        setType(type);
        setSort(sort);
        setIsLink(isLink);
        setMeta(meta);
    }

    @Override
    public Long id() {
        return id;
    }

    public void setParentIds(List<Long> parentIds) {
        if (ExtendObjects.isEmpty(parentIds)) {
            parentIds = Lists.newArrayList();
            this.parentId = DEFAULT_PERMISSION_PARENT;
        } else {
            this.parentId = parentIds.get(parentIds.size() - 1);
        }
        this.parentIds = parentIds;
    }

    public void setType(Type type) {
        if (ExtendObjects.isNull(type)) {
            type = Type.menu;
        }
        this.type = type;
    }

    public void setSort(Integer sort) {
        if (ExtendObjects.isNull(sort)) {
            sort = 0;
        }
        this.sort = sort;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setPath(String path) {
        if (ExtendObjects.isBlank(path)) {
            path = IdGenerate.stringId();
        }
        this.path = path;
        this.name = path.replace("/", "-");
    }

    public void setMeta(Meta meta) {
        Assert.notNull(meta, "元数据不能为空");
        this.meta = meta;
    }

    @Override
    public Permission root() {
        return this;
    }

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public String[] permSplitToArray() {
        String _perm = ExtendObjects.requireNonNullElse(ExtendObjects.replaceBlank(meta.getPerm()), "");
        return _perm.split(SEPARATOR);
    }

    public void setChildren(List<Permission> children) {
        if (ExtendObjects.isNull(children)) {
            this.children = List.of();
            return;
        }
        this.children = children;
    }

    public String getTitle() {
        return this.meta.getTitle();
    }

}

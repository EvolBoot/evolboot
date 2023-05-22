package org.evolboot.identity.domain.permission.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

import static org.evolboot.identity.IdentityI18nMessage.Permission.titleNotEmpty;

/**
 * @author evol
 */
@Entity
@Table(name = "evoltb_identity_permission")
@Getter
@Slf4j
@NoArgsConstructor
public class Permission extends JpaAbstractEntity<Long> implements AggregateRoot<Permission> {

    public static final long DEFAULT_PERMISSION_PARENT = 0L;

    public static final String SEPARATOR = ";";


    @Id
    private Long id;

    private Long parentId;

    private String title;

    private String perm;

    private String path;

    private Type type;

    private Integer sort;

    private String icon;

    private String remark;

    @Transient
    private List<Permission> children;

    @Builder
    public Permission(
            Long parentId,
            String title,
            String perm,
            String path,
            Type type,
            Integer sort,
            String icon,
            String remark) {
        generateId();
        setParentId(parentId);
        setTitle(title);
        setPerm(perm);
        setPath(path);
        setType(type);
        setSort(sort);
        setIcon(icon);
        setRemark(remark);
    }

    @Override
    public Long id() {
        return id;
    }

    public Permission setParentId(Long parentId) {
        if (ExtendObjects.isNull(parentId)) {
            parentId = DEFAULT_PERMISSION_PARENT;
        }
        this.parentId = parentId;
        return this;
    }

    public Permission setTitle(String title) {
        Assert.notBlank(title, titleNotEmpty());
        this.title = title;
        return this;
    }

    public Permission setPerm(String perm) {
        this.perm = perm;
        return this;
    }

    public Permission setPath(String url) {
        this.path = url;
        return this;
    }

    public Permission setType(Type type) {
        if (ExtendObjects.isNull(type)) {
            type = Type.PERM;
        }
        this.type = type;
        return this;
    }

    public Permission setSort(Integer sort) {
        if (ExtendObjects.isNull(sort)) {
            sort = 0;
        }
        this.sort = sort;
        return this;
    }


    public Permission setIcon(String uiIcon) {
        this.icon = uiIcon;
        return this;
    }

    public Permission setRemark(String remark) {
        this.remark = remark;
        return this;
    }


    @Override
    public Permission root() {
        return this;
    }

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public String[] permSplitToArray() {
        String _perm = ExtendObjects.requireNonNullElse(ExtendObjects.replaceBlank(perm), "");
        return _perm.split(SEPARATOR);
    }

    public void setChildren(List<Permission> children) {
        if (ExtendObjects.isNull(children)) {
            this.children = List.of();
            return;
        }
        this.children = children;
    }

    public String getKey() {
        return id.toString();
    }
}

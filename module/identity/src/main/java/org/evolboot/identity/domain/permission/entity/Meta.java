package org.evolboot.identity.domain.permission.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.util.Assert;

import jakarta.persistence.Embeddable;

/**
 * @author evol
 */
@NoArgsConstructor
@Embeddable
@Getter
@Setter
public class Meta {

    private String title;
    private String icon;
    private Boolean isHide = false;
    private Boolean isKeepAlive = false;
    private Boolean isAffix = false;
    private String link;
    private Boolean isIframe = false;
    private String perm;
    private String layout;

    public void setTitle(String title) {
        Assert.notBlank(title, "菜单名称不能为空");
        this.title = title;
    }

}

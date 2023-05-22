package org.evolboot.system.domain.startuppage.service;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.system.domain.startuppage.entity.StartupPageLocale;

import java.util.List;

/**
 * 启动页
 *
 * @author evol
 */
@Setter
@Getter
public abstract class StartupPageRequestBase {

    private Integer sort = 0;

    private Boolean enable = true;

    private List<StartupPageLocale> locales;
}

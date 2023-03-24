package org.evolboot.system.domain.startuppage;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 启动页
 *
 * @author evol
 * 
 */
@Setter
@Getter
public abstract class StartupPageRequestBase {

    private Integer sort = 0;

    private Boolean enable = true;

    private List<StartupPageLocale> locales;
}

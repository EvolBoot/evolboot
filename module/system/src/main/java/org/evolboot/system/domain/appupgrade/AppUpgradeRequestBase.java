package org.evolboot.system.domain.appupgrade;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * APP更新
 *
 * @author evol
 */
@Setter
@Getter
public abstract class AppUpgradeRequestBase {

    private String appVersion;

    private String upgradeDate;

    private String downloadUrl;

    private ClientType clientType;

    private Boolean showDialog = true;

    private List<AppUpgradeLocale> locales;
}

package org.evolboot.system.domain.appupgrade;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
@EqualsAndHashCode
public class AppUpgradeLocale implements LocaleLanguage {

    private String language;

    private String title;

    private String upgradeInfo;

}

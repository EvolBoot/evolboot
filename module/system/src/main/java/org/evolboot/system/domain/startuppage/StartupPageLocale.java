package org.evolboot.system.domain.startuppage;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 */
@Getter
@Setter
public class StartupPageLocale implements LocaleLanguage {

    private String language;

    private String cover;

}

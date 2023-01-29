package org.evolboot.content.domain.startuppage;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
public class StartupPageLocale implements LocaleLanguage {

    private String language;

    private String cover;

}

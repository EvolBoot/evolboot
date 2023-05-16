package org.evolboot.system.domain.banner;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 */
@Getter
@Setter
public class BannerLocale implements LocaleLanguage {

    private String language;

    private String link;

    private String cover;

    private String text;


}

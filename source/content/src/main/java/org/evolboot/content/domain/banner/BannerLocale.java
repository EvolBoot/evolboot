package org.evolboot.content.domain.banner;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 * 
 */
@Getter
@Setter
public class BannerLocale implements LocaleLanguage {

    private String language;

    private String link;

    private String cover;

    private String text;


}

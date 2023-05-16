package org.evolboot.system.domain.news;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 */
@Getter
@Setter
public class NewsLocale implements LocaleLanguage {

    private String language;

    private String title;
    private String summary;
    private String cover;
    private String content;

}

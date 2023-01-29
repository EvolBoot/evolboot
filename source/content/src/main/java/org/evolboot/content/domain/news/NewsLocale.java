package org.evolboot.content.domain.news;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 * 
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

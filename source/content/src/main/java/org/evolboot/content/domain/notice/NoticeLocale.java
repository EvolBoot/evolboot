package org.evolboot.content.domain.notice;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 * 
 */
@Getter
@Setter
public class NoticeLocale implements LocaleLanguage {

    private String language;

    private String title;

    private String content;

}

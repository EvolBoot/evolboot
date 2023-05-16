package org.evolboot.system.domain.notice;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 */
@Getter
@Setter
public class NoticeLocale implements LocaleLanguage {

    private String language;

    private String title;

    private String content;

}

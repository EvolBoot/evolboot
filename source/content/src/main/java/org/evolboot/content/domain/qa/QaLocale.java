package org.evolboot.content.domain.qa;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 * 
 */
@Getter
@Setter
@EqualsAndHashCode
public class QaLocale implements LocaleLanguage {

    private String language;

    private String title;

    private String content;

}

package org.evolboot.system.domain.qa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 */
@Getter
@Setter
@EqualsAndHashCode
public class QaLocale implements LocaleLanguage {

    private String language;

    private String title;

    private String content;

}

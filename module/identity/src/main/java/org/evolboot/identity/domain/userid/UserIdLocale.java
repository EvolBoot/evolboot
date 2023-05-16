package org.evolboot.identity.domain.userid;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 */
@Getter
@Setter
public class UserIdLocale implements LocaleLanguage {

    private String language;


}

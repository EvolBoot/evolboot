package org.evolboot.pay.domain.releasedorder;

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
public class ReleasedOrderLocale implements LocaleLanguage {

    private String language;


}

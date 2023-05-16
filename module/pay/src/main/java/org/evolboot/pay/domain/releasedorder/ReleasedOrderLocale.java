package org.evolboot.pay.domain.releasedorder;

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
public class ReleasedOrderLocale implements LocaleLanguage {

    private String language;


}

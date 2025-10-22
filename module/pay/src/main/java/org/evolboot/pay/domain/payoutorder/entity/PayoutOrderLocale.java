package org.evolboot.pay.domain.payoutorder.entity;

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
public class PayoutOrderLocale implements LocaleLanguage {

    private String language;


}

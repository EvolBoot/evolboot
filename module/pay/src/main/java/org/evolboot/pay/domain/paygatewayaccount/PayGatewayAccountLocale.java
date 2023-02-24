package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
@EqualsAndHashCode
public class PayGatewayAccountLocale implements LocaleLanguage {

    private String language;

    private String name;


}

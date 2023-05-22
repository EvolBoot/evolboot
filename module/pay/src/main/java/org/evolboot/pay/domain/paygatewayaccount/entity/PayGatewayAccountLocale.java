package org.evolboot.pay.domain.paygatewayaccount.entity;

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
public class PayGatewayAccountLocale implements LocaleLanguage {

    private String language;

    private String name;


}

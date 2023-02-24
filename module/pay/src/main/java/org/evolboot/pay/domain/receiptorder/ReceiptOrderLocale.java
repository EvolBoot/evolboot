package org.evolboot.pay.domain.receiptorder;

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
public class ReceiptOrderLocale implements LocaleLanguage {

    private String language;


}

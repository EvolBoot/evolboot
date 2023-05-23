package org.evolboot.pay.domain.receiptorder.entity;

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
public class ReceiptOrderLocale implements LocaleLanguage {

    private String language;


}

package org.evolboot.core.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

/**
 * @author evol
 */
public class DefaultMessageSourceAccessor extends MessageSourceAccessor {

    public DefaultMessageSourceAccessor(MessageSource messageSource) {
        super(messageSource);
    }

    public DefaultMessageSourceAccessor(MessageSource messageSource, Locale defaultLocale) {
        super(messageSource, defaultLocale);
    }


}

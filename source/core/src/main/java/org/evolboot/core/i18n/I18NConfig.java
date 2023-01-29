package org.evolboot.core.i18n;

import org.springframework.context.MessageSource;

/**
 * @author evol
 */
public abstract class I18NConfig {

    public static void config(MessageSource messageSource) {

        MessageHolder.setMessages(new DefaultMessageSourceAccessor(messageSource));
    }

}

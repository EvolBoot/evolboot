package org.evolboot.core.i18n;


/**
 * @author evol
 */
public abstract class I18NConfig {

    public static void config(I18NMessage i18NMessage) {
        I18NMessageHolder.setMessages(i18NMessage);
    }

}

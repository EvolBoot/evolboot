package org.evolboot.core.i18n;


import org.evolboot.core.util.ExtendObjects;

import java.util.List;

import static org.evolboot.core.i18n.Messages.codeKey;

/**
 * I18N 消息内容
 */
public abstract class I18NMessageHolder {

    private static I18NMessage messages;

    protected static void setMessages(I18NMessage messages) {
        I18NMessageHolder.messages = messages;
    }

    public static String message(String code) {
        return messages.message(code);
    }


    public static String message(String code, Object... args) {
        return messages.message(code, args);
    }

    public static String message(String code, String defaultMessage, Object... args) {
        return messages.message(code, defaultMessage);
    }

  /*  public static String message(String code, List<?> args) {
        return messages.message(code, args);
    }

    public static String message(String code, List<?> args, String defaultMessage) {
        return messages.message(code, args, defaultMessage);
    }*/

}

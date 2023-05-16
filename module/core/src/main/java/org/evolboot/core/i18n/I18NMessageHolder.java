package org.evolboot.core.i18n;


import org.evolboot.core.util.ExtendObjects;

/**
 * I18N 消息内容
 */
public abstract class I18NMessageHolder {

    private static I18NMessage messages;

    protected static void setMessages(I18NMessage messages) {
        I18NMessageHolder.messages = messages;
    }

    public static String message(String code) {
        String message = messages.message(code);
        if (ExtendObjects.isEmpty(message)) {
            return code;
        }
        return message;
    }


    public static String message(String code, Object... args) {
        String message = messages.message(code, args);
        if (ExtendObjects.isEmpty(message)) {
            return code;
        }
        return message;
    }
}

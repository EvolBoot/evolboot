package org.evolboot.core.i18n;


import java.util.List;

import static org.evolboot.core.i18n.Messages.codeKey;

/**
 * I18N 消息内容
 */
public abstract class MessageHolder {

    private static DefaultMessageSourceAccessor messages;

    public static void setMessages(DefaultMessageSourceAccessor messages) {
        MessageHolder.messages = messages;
    }

    public static String message(String code) {
        return messages.getMessage(codeKey(code));
    }

    public static String message(String code, List<?> args) {
        return messages.getMessage(codeKey(code), args.toArray());
    }

    public static String message(String code, String defaultMessage, Object... args) {
        return messages.getMessage(codeKey(code), args, defaultMessage);
    }

    public static String message(String code, List<?> args, String defaultMessage) {
        return messages.getMessage(codeKey(code), args.toArray(), defaultMessage);
    }
}

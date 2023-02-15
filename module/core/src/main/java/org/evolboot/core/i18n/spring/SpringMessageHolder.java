package org.evolboot.core.i18n.spring;


import org.evolboot.core.i18n.DefaultMessageSourceAccessor;
import org.evolboot.core.i18n.I18NMessage;

import java.util.List;

import static org.evolboot.core.i18n.Messages.codeKey;

/**
 * I18N 消息内容
 */
public class SpringMessageHolder implements I18NMessage {

    private DefaultMessageSourceAccessor messages;

    public SpringMessageHolder(DefaultMessageSourceAccessor messages) {
        this.messages = messages;
    }

    public String message(String code) {
        return messages.getMessage(codeKey(code));
    }

    public String message(String code, List<?> args) {
        return messages.getMessage(codeKey(code), args.toArray());
    }

    public String message(String code, String defaultMessage, Object... args) {
        return messages.getMessage(codeKey(code), args, defaultMessage);
    }

    public String message(String code, List<?> args, String defaultMessage) {
        return messages.getMessage(codeKey(code), args.toArray(), defaultMessage);
    }
}

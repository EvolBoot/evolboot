package org.evolboot.core.exception;

import org.evolboot.core.CoreI18nMessage;

/**
 * @author evol
 * 
 */
public class ConfigurationRepeatSettingException extends RuntimeException {

    public ConfigurationRepeatSettingException() {
        super(CoreI18nMessage.configurationRepeatSettingException());
    }

    public ConfigurationRepeatSettingException(String message) {
        super(message);
    }
}

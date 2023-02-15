package org.evolboot.core.i18n;


import org.evolboot.core.CoreI18nMessage;
import org.evolboot.core.util.Assert;

import java.util.List;


public abstract class MessageAssert {

    private static String message(String codeKey, String defaultMessage) {
        return I18NMessageHolder.message(codeKey, defaultMessage);
    }

    public static void notBlank(String str, String field) {
        Assert.notBlank(str, CoreI18nMessage.notBlank(field));
    }

    public static void notEmpty(String str, String field) {
        Assert.notEmpty(str, CoreI18nMessage.notEmpty(field));
    }

    public static void isTrue(boolean bool, String code) {
        Assert.isTrue(bool, message(code, "must not be empty"));
    }

    public static void notNull(Object obj, String field) {
        Assert.notNull(obj, CoreI18nMessage.notNull(field));

    }


}

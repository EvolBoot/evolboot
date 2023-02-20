package org.evolboot.core.i18n;


import org.evolboot.core.CoreI18nMessage;
import org.evolboot.core.exception.ExtendIllegalArgumentException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public abstract class I18NMessageAssert {

    private static String message(String codeKey) {
        return I18NMessageHolder.message(codeKey);
    }

    public static void fieldNotBlank(String str, String field) {
        Assert.notBlank(str, I18NMessageHolder.message(CoreI18nMessage.NOT_BLANK,  field));
    }

    public static void fieldNotEmpty(String str, String field) {
        notEmpty(str, I18NMessageHolder.message(CoreI18nMessage.NOT_EMPTY,  field));
    }


    public static void isTrue(boolean expression, String code) {
        if (!expression) {
            throw new ExtendIllegalArgumentException(message(code));
        }
    }

    public static void isNull(@Nullable Object object, String code) {
        if (object != null) {
            throw new ExtendIllegalArgumentException(message(code));
        }
    }

    public static void notNull(@Nullable Object object, String code) {
        if (object == null) {
            throw new ExtendIllegalArgumentException(message(code));
        }
    }


    public static void notEmpty(@Nullable Object[] array, String code) {
        if (ObjectUtils.isEmpty(array)) {
            throw new ExtendIllegalArgumentException(message(code));
        }
    }


    public static void notEmpty(@Nullable Collection<?> collection, String code) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ExtendIllegalArgumentException(message(code));
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map, String code) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ExtendIllegalArgumentException(message(code));
        }
    }


    public static void notEmpty(String str, String code) {
        if (ExtendObjects.isEmpty(str)) {
            throw new ExtendIllegalArgumentException(message(code));
        }
    }

    public static boolean notBlank(final CharSequence cs, String code) {
        if (ExtendObjects.isBlank(cs)) {
            throw new ExtendIllegalArgumentException(message(code));
        }
        return true;
    }

}

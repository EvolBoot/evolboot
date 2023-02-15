package org.evolboot.core.i18n;

import org.evolboot.core.exception.ExtendIllegalArgumentException;
import org.evolboot.core.util.ExtendObjects;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 断言，国际化消息
 * @author evol
 */
public class I18NAssert {

    public static void isTrue(boolean expression, String code) {
        if (!expression) {
            throw new ExtendIllegalArgumentException(I18NMessageHolder.message(code));
        }
    }

    public static void isNull(@Nullable Object object, String code) {
        if (object != null) {
            throw new ExtendIllegalArgumentException(I18NMessageHolder.message(code));
        }
    }

    public static void notNull(@Nullable Object object, String code) {
        if (object == null) {
            throw new ExtendIllegalArgumentException(I18NMessageHolder.message(code));
        }
    }


    public static void notEmpty(@Nullable Object[] array, String code) {
        if (ObjectUtils.isEmpty(array)) {
            throw new ExtendIllegalArgumentException(I18NMessageHolder.message(code));
        }
    }


    public static void notEmpty(@Nullable Collection<?> collection, String code) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ExtendIllegalArgumentException(I18NMessageHolder.message(code));
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map, String code) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ExtendIllegalArgumentException(I18NMessageHolder.message(code));
        }
    }


    public static void notEmpty(String str, String code) {
        if (ExtendObjects.isEmpty(str)) {
            throw new ExtendIllegalArgumentException(I18NMessageHolder.message(code));
        }
    }

    public static boolean notBlank(final CharSequence cs, String code) {
        if (ExtendObjects.isBlank(cs)) {
            throw new ExtendIllegalArgumentException(I18NMessageHolder.message(code));
        }
        return true;
    }



}

package org.evolboot.core.util;


import org.apache.logging.log4j.util.Strings;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 扩展Objects
 *
 * @author evol
 */
public final class ExtendObjects {


    public static boolean equals(Object a, Object b) {
        return Objects.equals(a, b);
    }


    public static boolean deepEquals(Object a, Object b) {
        return Objects.deepEquals(a, b);
    }


    public static int hashCode(Object o) {
        return Objects.hashCode(o);
    }


    public static int hash(Object... values) {
        return Objects.hash(values);
    }


    public static String toString(Object o) {
        return Objects.toString(o);
    }


    public static String toString(Object o, String nullDefault) {
        return Objects.toString(o, nullDefault);
    }


    public static <T> int compare(T a, T b, Comparator<? super T> c) {
        return Objects.compare(a, b, c);
    }


    public static <T> T requireNonNull(T obj) {
        return Objects.requireNonNull(obj);
    }


    public static <T> T requireNonNull(T obj, String message) {
        return Objects.requireNonNull(obj, message);
    }


    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }


    public static boolean isNotBlank(String obj) {
        return Strings.isNotBlank(obj);
    }

    public static boolean isBlank(String obj) {
        return Strings.isBlank(obj);
    }

    public static boolean nonNull(Object obj) {
        return obj != null;
    }


    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isBlank(final CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    public static <T> T requireNonNullElse(T obj, T defaultObj) {
        return Objects.requireNonNullElse(obj, defaultObj);
    }


    public static <T> T requireNonNullElseGet(T obj, Supplier<? extends T> supplier) {
        return Objects.requireNonNullElseGet(obj, supplier);
    }

    public static <T> T requireNonNull(T obj, Supplier<String> messageSupplier) {
        return Objects.requireNonNull(obj, messageSupplier);
    }


    public static String trimToNull(String str) {
        return str == null ? null : str.trim();
    }


    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}

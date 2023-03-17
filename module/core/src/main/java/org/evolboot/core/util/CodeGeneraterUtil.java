package org.evolboot.core.util;

/**
 * @author evol
 */
public class CodeGeneraterUtil {

    public static String get4Number() {
        Double v = Math.random() * 9000 + 1000;
        int i = v.intValue();
        return Integer.toString(i);
    }



    public static String get6Number() {
        Double v = Math.random() * 900000 + 100000;
        int i = v.intValue();
        return Integer.toString(i);
    }

}

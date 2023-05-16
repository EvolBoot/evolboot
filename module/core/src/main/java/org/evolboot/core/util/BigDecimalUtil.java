package org.evolboot.core.util;

import java.math.BigDecimal;

/**
 * @author evol
 */
public final class BigDecimalUtil {

    public static final BigDecimal oneHundred = BigDecimal.valueOf(100);

    /**
     * left >=  right
     *
     * @param left
     * @param right
     * @return
     */
    public static boolean goe(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) >= 0;
    }

    /**
     * left >  right
     *
     * @param left
     * @param right
     * @return
     */
    public static boolean gt(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) > 0;
    }

    /**
     * left >  Zero
     *
     * @param left
     * @return
     */
    public static boolean gtZero(BigDecimal left) {
        return left.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * left >=  Zero
     *
     * @param left
     * @return
     */
    public static boolean goeZero(BigDecimal left) {
        return left.compareTo(BigDecimal.ZERO) >= 0;
    }

    /**
     * left <=  Zero
     *
     * @param left
     * @return
     */
    public static boolean loeZero(BigDecimal left) {
        return left.compareTo(BigDecimal.ZERO) <= 0;
    }

    /**
     * left <  Zero
     *
     * @param left
     * @return
     */
    public static boolean leZero(BigDecimal left) {
        return left.compareTo(BigDecimal.ZERO) < 0;
    }

    /**
     * left =  Zero
     *
     * @param decimal
     * @return
     */
    public static boolean eqZero(BigDecimal decimal) {
        return decimal.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * left <  right
     *
     * @param left
     * @param right
     * @return
     */
    public static boolean le(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) < 0;
    }

    /**
     * left <=  right
     *
     * @param left
     * @param right
     * @return
     */
    public static boolean loe(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) <= 0;
    }

    /**
     * left ==  right
     *
     * @param left
     * @param right
     * @return
     */
    public static boolean eq(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) == 0;
    }

}

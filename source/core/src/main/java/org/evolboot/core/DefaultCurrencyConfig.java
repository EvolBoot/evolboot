package org.evolboot.core;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author evol
 * 
 */
public abstract class DefaultCurrencyConfig {
    /**
     * 四舍五入模式
     */
    public final static RoundingMode roundingMode = RoundingMode.HALF_UP;

    private final static BigDecimal minimum = BigDecimal.valueOf(0.01);

    /**
     * 保留精度
     */
    public final static int scale = 2;


    public static String parseMoneyToString(BigDecimal money) {
        return parseMoney(money).toString();
    }

    public static BigDecimal parseMoney(BigDecimal money) {
        return money.setScale(scale, roundingMode);
    }

    public static RoundingMode getRoundingMode() {
        return roundingMode;
    }

    public static int getScale() {
        return scale;
    }

    public static BigDecimal getMinimum() {
        return minimum;
    }

    public static void main(String[] args) {
    }
}

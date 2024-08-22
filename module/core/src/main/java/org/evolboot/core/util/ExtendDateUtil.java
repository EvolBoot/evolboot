package org.evolboot.core.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * @author evol
 */
public class ExtendDateUtil {

    /**
     * 获取某年的结束时间
     *
     * @param date 日期
     * @return {@link DateTime}
     */
    public static DateTime endOfYear(Date date) {
        return DateUtil.endOfYear(date);
    }

    /**
     * 获取某年的开始时间
     *
     * @param date 日期
     * @return {@link DateTime}
     */
    public static DateTime beginOfYear(Date date) {
        return DateUtil.beginOfYear(date);
    }

    /**
     * 获取某月的结束时间
     *
     * @param date 日期
     * @return {@link DateTime}
     */
    public static DateTime endOfMonth(Date date) {
        return DateUtil.endOfMonth(date);
    }

    /**
     * 获取某月的开始时间
     *
     * @param date 日期
     * @return {@link DateTime}
     */
    public static DateTime beginOfMonth(Date date) {
        return DateUtil.beginOfMonth(date);
    }

    /**
     * 获取某天的结束时间
     *
     * @param date 日期
     * @return {@link DateTime}
     */
    public static DateTime endOfDay(Date date) {
        DateTime dateTime = DateUtil.endOfDay(date);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(dateTime);
        calendarEnd.set(Calendar.MILLISECOND, 0);
        return new DateTime(calendarEnd);
    }

    /**
     * 偏移天
     *
     * @param date   日期
     * @param offset 偏移天数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static DateTime offsetDay(Date date, int offset) {
        return DateUtil.offsetDay(date, offset);
    }

    /**
     * 偏移天
     *
     * @param offset 偏移天数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static DateTime offsetDay(int offset) {
        return DateUtil.offsetDay(new Date(), offset);
    }


    /**
     * 偏移秒数
     *
     * @param date   日期
     * @param offset 偏移秒数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static DateTime offsetSecond(Date date, int offset) {
        return DateUtil.offsetSecond(date, offset);
    }

    /**
     * 偏移分钟
     *
     * @param date   日期
     * @param offset 偏移分钟数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static DateTime offsetMinute(Date date, int offset) {
        return DateUtil.offsetMinute(date, offset);
    }

    /**
     * /**
     * 获取某天的开始时间
     *
     * @param date 日期
     * @return {@link DateTime}
     */
    public static DateTime beginOfDay(Date date) {
        return DateUtil.beginOfDay(date);
    }

    /**
     * 判断两个日期相差的天数<br>
     *
     * <pre>
     * 有时候我们计算相差天数的时候需要忽略时分秒。
     * 比如：2016-02-01 23:59:59和2016-02-02 00:00:00相差一秒
     * 如果isReset为<code>false</code>相差天数为0。
     * 如果isReset为<code>true</code>相差天数将被计算为1
     * </pre>
     *
     * @param beginDate 起始日期
     * @param endAt   结束日期
     * @param isReset   是否重置时间为起始时间
     * @return 日期差
     * @since 3.0.1
     */
    public static long betweenDay(Date beginDate, Date endAt, boolean isReset) {
        return DateUtil.betweenDay(beginDate, endAt, isReset);
    }


    /**
     * 根据特定格式格式化日期
     *
     * @param date   被格式化的日期
     * @param format 日期格式，常用格式见： {@link DatePattern}
     * @return 格式化后的字符串
     */
    public static String format(Date date, String format) {
        return DateUtil.format(date, format);
    }


    public static Integer intOfToDay() {
        return parseInt(DateTime.now().toString("yyyyMMdd"));
    }

    public static Integer intOfYesterday() {
        return parseInt(DateUtil.offsetDay(DateTime.now(), -1).toString("yyyyMMdd"));
    }

    /**
     * left >= right
     *
     * @param left
     * @param right
     * @return
     */
    public static boolean goe(Date left, Date right) {
        return left.getTime() >= right.getTime();
    }

    /**
     * left >= now
     *
     * @param left
     * @return
     */
    public static boolean goeNow(Date left) {
        return left.getTime() >= new Date().getTime();
    }


    /**
     * left <= now
     *
     * @param left
     * @return
     */
    public static boolean loeNow(Date left) {
        return left.getTime() <= new Date().getTime();
    }

    /**
     * left <= right
     *
     * @param left
     * @param right
     * @return
     */
    public static boolean loe(Date left, Date right) {
        return left.getTime() <= right.getTime();
    }

    /**
     * 当前时间，格式 yyyy-MM-dd HH:mm:ss
     *
     * @return 当前时间的标准形式字符串
     */
    public static String now() {
        return DateUtil.now();
    }


}

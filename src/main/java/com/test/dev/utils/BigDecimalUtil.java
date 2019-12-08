package com.test.dev.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/***
 * shidong created by 2018/6/7
 */
public class BigDecimalUtil {

    public static final int MONEY_POINT = 2; // 货币保留两位小数

    /**
     * 格式化精度
     *
     * @param v
     * @param point 小数位数
     * @return double
     */
    public static Double format(final double v, final int point) {
        final BigDecimal b = new BigDecimal(v);
        return b.setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Double format(final double v) {
        return format(v, 2);
    }

    public static Double formatDown(final double v, final int point) {
        final BigDecimal b = new BigDecimal(v);
        return b.setScale(point, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static Double formatDown(final double v) {
        return formatDown(v, 2);
    }

    public static Double formatUp(final double v, final int point) {
        final BigDecimal b = new BigDecimal(v);
        return b.setScale(point, BigDecimal.ROUND_UP).doubleValue();
    }

    public static Double formatUp(final double v) {
        return formatUp(v, 2);
    }


    /**
     * @param v
     * @param point
     * @return
     */
    public static Double formatRoundUp(final double v, final int point) {
        final NumberFormat nf = NumberFormat.getInstance();
        nf.setRoundingMode(RoundingMode.HALF_UP);// 设置四舍五入
        nf.setMinimumFractionDigits(point);// 设置最小保留几位小数
        nf.setMaximumFractionDigits(point);// 设置最大保留几位小数
        return Double.valueOf(nf.format(v));
    }

    /**
     * 格式化金额。带千位符
     *
     * @param v
     * @return
     */
    public static String moneyFormat(final Double v) {
        final DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(3);
        formater.setRoundingMode(RoundingMode.FLOOR);
        return formater.format(v.doubleValue());
    }

    /**
     * 带小数的显示小数。不带小数的显示整数
     *
     * @param d
     * @return
     */
    public static String doubleTrans(final Double d) {
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d.doubleValue());
        }
        return String.valueOf(d);
    }

    /**
     * BigDecimal 相加
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static Double add(final double v1, final double v2) {
        final BigDecimal n1 = new BigDecimal(Double.toString(v1));
        final BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.add(n2).doubleValue();
    }

    /**
     * BigDecimal 相减
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static Double subtract(final double v1, final double v2) {
        final BigDecimal n1 = new BigDecimal(Double.toString(v1));
        final BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.subtract(n2).doubleValue();
    }

    /**
     * BigDecimal 相乘
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static Double multiply(final double v1, final double v2) {
        final BigDecimal n1 = new BigDecimal(Double.toString(v1));
        final BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.multiply(n2).doubleValue();
    }

    /**
     * BigDecimal 相除
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static Double divide(final double v1, final double v2) {
        final BigDecimal n1 = new BigDecimal(Double.toString(v1));
        final BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.divide(n2, 10, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 比较大小 小于0：v1 < v2 大于0：v1 > v2 等于0：v1 = v2
     *
     * @param v1
     * @param v2
     * @return
     */
    public static int compare(final double v1, final double v2) {
        final BigDecimal n1 = new BigDecimal(Double.toString(v1));
        final BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.compareTo(n2);
    }
}

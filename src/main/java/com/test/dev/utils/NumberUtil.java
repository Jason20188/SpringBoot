package com.test.dev.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * User: shidong01
 * Date: 2019-10-25
 * Time: 11:41
 * Description: No Description
 */
@Slf4j
public class NumberUtil {

    public static Double getDoubleFromObj(Object rate) {
        if (null == rate) return 0D;
        try {
            return Double.valueOf(rate + "");
        } catch (Exception e) {
            log.error("解析数据出错", e);
            return 0D;
        }
    }

    public static Integer getIntegerFromObj(Object rate) {
        if (null == rate) return 0;
        try {
            return Integer.valueOf(rate + "");
        } catch (Exception e) {
            log.error("解析数据出错", e);
            return 0;
        }
    }
}
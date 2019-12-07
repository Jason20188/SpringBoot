package com.test.dev.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Months;

import java.util.Calendar;

/**
 * @author zhanghui18
 * @date 2019/10/14 10:40
 * @description 身份证相关
 */
public class IDCardUtil {


    /**
     * 根据身份证获取年龄
     *
     * @param idCard
     * @return
     */
    public static int getUserAge(String idCard) {
        int age = -1;
        if (StringUtils.isEmpty(idCard)) {
            return age;
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = idCard.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return age;
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return age;
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && idCard.length() == 15) {
            age = (year - Integer.parseInt("19" + idCard.substring(6, 8)));
        } else if (flag && idCard.length() == 18) {
            age = (year - Integer.parseInt(idCard.substring(6, 10)));
        }
        return age;
    }

    /**
     * 身份证是否有效
     *
     * @param period 有效期间
     * @param month  有效期月份
     * @return
     */
    public static boolean valid(String period, int month) {
        String[] dates = period.split("-");
        if (dates == null || dates.length < 2) {
            return false;
        }
        if (dates[1].equals("长期")) {
            return true;
        }
        DateTime dateTime = DateUtil.parseDateTime(dates[1], "yyyy.MM.dd");
        int months = Months.monthsBetween(dateTime, DateTime.now()).getMonths();
        //有效期内
        if (months >= month) {
            return true;
        }
        return false;
    }

}

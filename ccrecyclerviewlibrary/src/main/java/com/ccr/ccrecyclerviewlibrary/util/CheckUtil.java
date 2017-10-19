package com.ccr.ccrecyclerviewlibrary.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

    /**
     * 判断两个string是否相等
     */
    public static boolean checkEquels(Object strObj0, Object strObj1) {
        String str0 = strObj0 + "";
        String str1 = strObj1 + "";
        return str0.equals(str1);
    }

    /**
     * 同时�?��多个参数是否为空
     *
     * @param strArray
     * @return
     */
    public static boolean isNull(Object... strArray) {
        for (Object obj : strArray) {
            if (!"".equals(obj + "")) {
                return false;
            }
        }
        return true;
    }

    /**
     * �?��是否为空
     */
    public static boolean isNull(Object strObj) {
        String str = strObj + "";
        return !(!"".equals(str) && !"null".equals(str));
    }

    /**
     * 邮箱
     *
     * @param strObj
     * @return
     */
    public static boolean checkEmail(Object strObj) {
        String str = String.valueOf(strObj);
        String match = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * �?��是否为电话号�?
     */
    public static boolean isMobile(Object phoneNumber) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([6-8]|0))|(18[0-9]))\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber + "");
        return matcher.matches();
    }

    /**
     * �?��str的长度是否达到要�?
     *
     * @param strObj
     * @param length
     * @return
     */
    public static boolean checkLength(Object strObj, int length) {
        String str = strObj + "";
        return str.length() >= length;
    }

    /**
     * 检查字符串的长度
     *
     * @param strObj
     * @param length
     * @return
     */
    public static boolean checkLengthEq(Object strObj, int length) {
        String str = strObj + "";
        return str.length() == length;
    }

    /**
     * @param @param  strObj
     * @param @param  min
     * @param @param  max
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: checkNum
     * @Description: 检查是否为数字，以及这个数在min与max之间，包含min与max
     */
    public static boolean checkNum(Object strObj, int min, int max) {
        String str = strObj + "";
        try {
            int number = Integer.parseInt(str);
            return number <= max && number >= min;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param @param  strObj
     * @param @param  min
     * @param @param  max
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: checkNumWithDecimal
     * @Description: 检查是否为数字，以及这个数在min与max之间，包含min与max,可以为小数
     */
    public static boolean checkNumWithDecimal(Object strObj, float min, float max) {
        String str = strObj + "";
        try {
            float number = Float.parseFloat(str);
            return number <= max && number >= min;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param @param  strObj
     * @param @param  start
     * @param @param  end
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: checkLength
     * @Description: 检查字符串的长度
     */
    public static boolean checkLength(Object strObj, int start, int end) {
        String str = strObj + "";
        return str.length() >= start && str.length() <= end;
    }

    /**
     * @param @param  strObj
     * @param @param  num  倍数
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: checkMoney
     * @Description: 检查金额是否为数字以及是否为一个数的倍数
     */
    public static boolean checkMoney(Object strObj, int num) {
        String str = strObj + "";
        try {
            int money = Integer.parseInt(str);
            return money % num == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * �?��string是否为o
     *
     * @param value
     * @return
     */
    public static boolean checkZero(String value) {
        int valueInt = StringUtil.toInt(value);
        return valueInt == 0;
    }
}

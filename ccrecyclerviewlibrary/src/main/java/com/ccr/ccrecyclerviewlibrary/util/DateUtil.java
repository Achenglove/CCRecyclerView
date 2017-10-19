package com.ccr.ccrecyclerviewlibrary.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期、时间
 *
 * @author zhangyb@ifenguo.com
 * @createDate 2015年3月10日
 */
public class DateUtil {

    public static final String yyMMdd = "yy-MM-dd";
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String HHmmss = "HH:mm:ss";
    public static final String HHmm = "HH:mm";
    public static final String yyMMddHHmmss = "yy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String yyMMddHHmm = "yy-MM-dd HH:mm";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String MMddHHmm = "MM-dd hh:mm";
    public static final String MMddHHmmss = "MM-dd hh:mm:ss";

    public static final String MMdd = "MM-dd";
    public static final String yyyyMM = "yyyy-MM";

    public static final String yyyymm = "yyyy-MM";
    public static final String yyyymmdd = "yyyy-MM-dd";

    public static Date parseToDate(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        if (s == null || s.length() < 5)
            return null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param @param  日期字符串
     * @param @param  日期字符串的格式，yyyyMdd等
     * @param @return 设定文件
     * @return long 返回类型
     * @throws
     * @Title: parseToDateLong
     * @Description: 将日期字符串转化为时间戳
     */
    public static long parseToDateLong(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        if (s == null || s.length() < 5)
            return 0;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static Date parseToTimesDate(String sTime) {
        Date date = null;
        if (sTime == null || "".equals(sTime)) {
            return null;
        }
        date = Timestamp.valueOf(sTime);
        return date;
    }

    public static String parseToString(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        String str = null;
        if (s == null || s.length() < 8)
            return null;
        try {
            date = simpleDateFormat.parse(s);
            str = simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String parseToString(Date date, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        String str = null;
        if (date == null)
            return null;
        str = simpleDateFormat.format(date);
        return str;
    }

    /**
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: getNowTime
     * @Description: 获取当前的日期，并以“yyyy-MM-dd HH:mm:ss”的形式显示
     */
    public static String getNowTime() {
        Date nowDate = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowDate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = formatter.format(now.getTime());
        return str;
    }


    public static String parseToStringWithLineBreak(long curentTime) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(curentTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = formatter.format(now.getTime());
        // String[] arrays = str.split("\\s+");
        str = str.replace("\\s", "\n");
        return str;
    }

    /**
     * @param @param  curentTime
     * @param @param  style
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: parseToString
     * @Description: 将时间戳转化为指定格式（style）的时间字符串
     */
    public static String parseToString(long curentTime, String style) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(curentTime);
        SimpleDateFormat formatter = new SimpleDateFormat(style);
        String str = formatter.format(now.getTime());
        return str;
    }


    public static String parseToDate(long time) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str = formatter.format(now.getTime());
        return str;
    }


    /**
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: getNowShortTime
     * @Description: 获取当前的日期，并以“yyyy-MM-dd”的形式显示
     */
    public static String getNowShortTime() {
        Date nowDate = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowDate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str = formatter.format(now.getTime());
        return str;
    }

    public static String getNextDate(String ts, int i) {
        Calendar now = Calendar.getInstance();
        Timestamp t = Timestamp.valueOf(ts + " 00:00:00");
        now.setTime(t);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        now.add(Calendar.DAY_OF_MONTH, +(i));
        String dt = formatter.format(now.getTime());
        return dt;
    }

    /**
     * @param @param  ts
     * @param @param  i
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: getNextMonth
     * @Description: 获取某个日期一个月前的日期，如获取2014-12-11的前一个月的日期为201411-11
     */
    public static String getNextMonth(String ts, int i) {
        Calendar now = Calendar.getInstance();
        Timestamp t = Timestamp.valueOf(ts + " 00:00:00");
        now.setTime(t);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        now.add(Calendar.MONTH, +(i));
        String dt = formatter.format(now.getTime());
        return dt;
    }

    public static String getNextMonth(int i) {
        return getNextMonth(DateUtil.getNowShortTime(), i);
    }

    public static String getNextTime(String ts, int i) {
        Calendar now = Calendar.getInstance();
        Timestamp t = Timestamp.valueOf(ts);
        System.out.println(t + "     " + ts);
        now.setTime(t);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        now.add(Calendar.MINUTE, +(i));
        String dt = formatter.format(now.getTime());
        return dt;
    }

    public static long formatDateMills(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        if (s == null || s.length() < 5)
            return 0;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    public static long getPreTime(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTimeInMillis();
    }

    public static int parseToInt(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String str = formatter.format(time);
        return StringUtil.toInt(str);
    }

    /**
     * 求两个日期相差天数
     *
     * @param sd 起始日期，格式yyyy-MM-dd
     * @param ed 终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(String sd, String ed) {
        return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date.valueOf(sd)).getTime())
                / (3600 * 24 * 1000);
    }

    /**
     * 求两个日期相差天数
     *
     * @param startTime 起始日期，格式yyyy-MM-dd
     * @param endTime   终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(long startTime, long endTime) {
        String sd = DateUtil.parseToDate(startTime);
        String ed = DateUtil.parseToDate(endTime);
        return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date.valueOf(sd)).getTime())
                / (3600 * 24 * 1000);
    }

    /**
     * 获取今天的时间戳（如果是周末，则调至最近的工作日）
     *
     * @return
     */
    public static long getNowTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        if (day_of_week == Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -2);
        } else if (day_of_week == Calendar.SATURDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        return calendar.getTimeInMillis();

    }

}
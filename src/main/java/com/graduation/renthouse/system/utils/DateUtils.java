package com.graduation.renthouse.system.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long year=l / (24 * 60 * 60 * 1000*30*12);
        long month=l / (24 * 60 * 60 * 1000*30);
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        String r = "";
        if(year>0){
            r += year + "年";
        }
        else if(month>0){
            r += month + "月";
        }
        else if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        }
        r += "前";
        return r;
    }

    public static String getTimeBeforeToDay(Date date) {
        String msg = "";
        Date now = new Date();
        long time = now.getTime() - date.getTime();
        if (time < 0) {
            return "输入的时间不对";
        } else {
            long dateTemp1 = time / 1000; // 秒
            long dateTemp2 = dateTemp1 / 60; // 分钟
            long dateTemp3 = dateTemp2 / 60; // 小时
            long dateTemp4 = dateTemp3 / 24; // 天数
            long dateTemp5 = dateTemp4 / 30; // 月数
            long dateTemp6 = dateTemp5 / 12; // 年数

            if(dateTemp6>0) {
                msg = dateTemp6 + "年前";
            }
            else if(dateTemp6<=0&&dateTemp5 > 0){
                msg=dateTemp5 + "个月前";
            }
            else if(dateTemp5<=0&&dateTemp4 > 0)
            {
                msg = dateTemp4 + "天前";
            }else if(dateTemp4<=0&&dateTemp3>0){
                msg=dateTemp3+"小时前";
            }else if(dateTemp3<=0&&dateTemp2>0){
                msg=dateTemp2+"分钟前";
            }else{
                msg="刚刚";
            }
            return msg;
        }
    }
    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    //得到n个月前  +之后  -之前
    public static  Date addMonth(int month){

        Calendar calendar=Calendar.getInstance();

        calendar.add(Calendar.MONTH,month);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    public static  Date monthFirstDate(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int actualMinimum = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, actualMinimum);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    public static  Date monthLastDate(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, actualMaximum);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,59);
        return calendar.getTime();
    }

    public static  Date dayFirstDate(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,00);
        calendar.set(Calendar.MINUTE,00);
        calendar.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,00);
        return calendar.getTime();
    }
    public static  Date dayLastDate(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,59);
        return calendar.getTime();
    }

    public static Date parseMonth(String s, String pattern)throws Exception {
        if (s != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(s);
        }
        return null;
    }

    public static String getBegin(){
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_PATTERN);
        Date begin=new Date();
        begin.setDate(1);
        return df.format(begin);
    }
    public static String getEnd(){
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_PATTERN);
        return df.format(new Date());
    }

    public static Date parse(Date date){
        Date date1=null;
        SimpleDateFormat sdf=new SimpleDateFormat(DATE_TIME_PATTERN);
        try {
             date1 = sdf.parse(format(date, DATE_TIME_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }
    public static void main(String[] args) throws Exception{
        System.out.println(addMonth(-3).toLocaleString());
        System.out.println(Calendar.getInstance().getTime().toLocaleString());

        Date date = monthFirstDate(new Date());
        Date date1 = monthLastDate(new Date());
        System.out.println(date);
        System.out.println(date1);

        Date date2 = parseMonth("2018-09", "yyyy-MM");
        System.out.println(date2);
    }

    public static String getMonthFirstDate(){
       return format(monthFirstDate(new Date()),DATE_TIME_PATTERN) ;
    }


}

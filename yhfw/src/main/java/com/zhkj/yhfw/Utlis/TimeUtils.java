package com.zhkj.yhfw.Utlis;

import android.util.Log;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    // 格式：年－月－日 小时：分钟：秒
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return    ts;
    }


    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp1(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return    ts;
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp1s(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return    ts;
    }

    /*
     * 是否大于当前时间
     */
    public static boolean dateGreater(String s) throws ParseException {
        long current_time = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        if (ts>current_time){
            return   true;
        }else {
            return   false;
        }
    }

    /*
     * 是否大于当前时间
     */
    public static boolean dateGreaters(String s) throws ParseException {
        long current_time = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        if (ts>current_time){
            return   true;
        }else {
            return   false;
        }
    }
    /*
     * 将时间戳转换为时间
     */
    public static String getDateToString(long milSecond) {
        Date date = new Date(milSecond* 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return format.format(date);
    }
    /*
     * 将时间戳转换为时间
     */
    public static String getDayToString(long milSecond) {
        Date date = new Date(milSecond* 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }




//    将秒数转换成时间
     public static  String GetTiem(long start_dt){
         long current_time = System.currentTimeMillis()/1000;
         long  counttime=(current_time-start_dt)*1000;
         String finaltime ="";
//          days + "天" + hours + "时" + minutes + "分" + second + "秒";
         long days = counttime / (1000 * 60 * 60 * 24);
         long hours = (counttime-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
         long minutes = (counttime-days*(1000 * 60 * 60 * 24)
                 -hours*(1000* 60 * 60))/(1000* 60);
         long second = (counttime-days*(1000 * 60 * 60 * 24)
                 -hours*(1000* 60 * 60)-minutes*(1000*60))/1000;
         if (days>0){
             if (hours<10){
                 if (minutes<10){
                     if (second<10){
                         finaltime = days+"天"+"0"+hours+":0"+minutes+":0"+second;
                     }else {
                         finaltime = days+"天"+"0"+hours+":0"+minutes+second;
                     }
                 }else {
                     if (second<10){
                         finaltime = days+"天"+":0"+hours+minutes+second;
                     }else {
                         finaltime = days+"天"+":0"+hours+minutes+second;
                     }
                 }
             }else {
                 if (minutes<10){
                     if (second<10){
                         finaltime = days+"天"+hours+":0"+minutes+":0"+second;
                     }else {
                         finaltime = days+"天"+hours+":0"+minutes+":"+second;
                     }
                 }else {
                     if (second<10){
                         finaltime = days+"天"+hours+":"+minutes+":0"+second;
                     }else {
                         finaltime = days+"天"+hours+":"+minutes+":"+second;
                     }
                 }
             }
         }else {
             if (hours<10){
                 if (minutes<10){
                     if (second<10){
                         finaltime ="0"+hours+":0"+minutes+":0"+second;
                     }else {
                         finaltime = "0"+hours+":0"+minutes+":"+second;
                     }
                 }else {
                     if (second<10){
                         finaltime = "0"+hours+":"+minutes+":0"+second;
                     }else {
                         finaltime = "0"+hours+":"+minutes+":"+second;
                     }
                 }
             }else {
                 if (minutes<10){
                     if (second<10){
                         finaltime = hours+":0"+minutes+":0"+second;
                     }else {
                         finaltime = hours+":0"+minutes+":"+second;
                     }
                 }else {
                     if (second<10){
                         finaltime = hours+":"+minutes+":0"+second;
                     }else {
                         finaltime = hours+":"+minutes+":"+second;
                     }
                 }
             }
         }
         return finaltime;
     }

//    获取订单时间
    public static  String GetOrderTime(long start_dt){
        long  counttime=start_dt*1000;
        String finaltime ="";
//          days + "天" + hours + "时" + minutes + "分" + second + "秒";
        long days = counttime / (1000 * 60 * 60 * 24);
        long hours = (counttime-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
        long minutes = (counttime-days*(1000 * 60 * 60 * 24)
                -hours*(1000* 60 * 60))/(1000* 60);
        long second = (counttime-days*(1000 * 60 * 60 * 24)
                -hours*(1000* 60 * 60)-minutes*(1000*60))/1000;
        if (days>0){
            if (hours<10){
                if (minutes<10){
                    if (second<10){
                        finaltime = days+"天"+"0"+hours+":0"+minutes+":0"+second;
                    }else {
                        finaltime = days+"天"+"0"+hours+":0"+minutes+second;
                    }
                }else {
                    if (second<10){
                        finaltime = days+"天"+":0"+hours+minutes+second;
                    }else {
                        finaltime = days+"天"+":0"+hours+minutes+second;
                    }
                }
            }else {
                if (minutes<10){
                    if (second<10){
                        finaltime = days+"天"+hours+":0"+minutes+":0"+second;
                    }else {
                        finaltime = days+"天"+hours+":0"+minutes+":"+second;
                    }
                }else {
                    if (second<10){
                        finaltime = days+"天"+hours+":"+minutes+":0"+second;
                    }else {
                        finaltime = days+"天"+hours+":"+minutes+":"+second;
                    }
                }
            }
        }else {
            if (hours<10){
                if (minutes<10){
                    if (second<10){
                        finaltime ="0"+hours+":0"+minutes+":0"+second;
                    }else {
                        finaltime = "0"+hours+":0"+minutes+":"+second;
                    }
                }else {
                    if (second<10){
                        finaltime = "0"+hours+":"+minutes+":0"+second;
                    }else {
                        finaltime = "0"+hours+":"+minutes+":"+second;
                    }
                }
            }else {
                if (minutes<10){
                    if (second<10){
                        finaltime = hours+":0"+minutes+":0"+second;
                    }else {
                        finaltime = hours+":0"+minutes+":"+second;
                    }
                }else {
                    if (second<10){
                        finaltime = hours+":"+minutes+":0"+second;
                    }else {
                        finaltime = hours+":"+minutes+":"+second;
                    }
                }
            }
        }
        return finaltime;
    }
    /**
     * 两个日期相减
     *
     * @param firstTime
     * @param secTime
     * @return 相减得到的秒数
     */
    public static long timeSub(String firstTime, String secTime) {
        long first = stringtoDate(firstTime, FORMAT_ONE).getTime();
        long second = stringtoDate(secTime, FORMAT_ONE).getTime();
        return (second - first) / 1000;
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     *
     * @param dateStr
     * @return
     */
    public static Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            // log.error(e);
            d = null;
        }
        return d;
    }


    /**
     * 取得指定日期过 months 月后的日期 (当 months 为负数表示指定月之前);
     *          日期 为null时表示当天
     *          相加(相减)的月数
     */
    public static Date nextMonth( int months) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 获得某月的天数
     *
     * @param year
     *          int
     * @param month
     *          int
     * @return int
     */
    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("1") || month.equals("3") || month.equals("5")
                || month.equals("7") || month.equals("8") || month.equals("10")
                || month.equals("12")) {
            days = 31;
        } else if (month.equals("4") || month.equals("6") || month.equals("9")
                || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
                    || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }

        return days;
    }

    /**
     * 获取某年某月的天数
     *
     * @param year
     *          int
     * @param month
     *          int 月份[1-12]
     * @return int
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month , 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}

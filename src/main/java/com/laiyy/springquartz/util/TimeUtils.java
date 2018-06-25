package com.laiyy.springquartz.util;

/**
 * @author laiyy
 * @date 2018/6/25 17:25
 * @description
 */
public class TimeUtils {

    private static final int MINUTE = 60;

    private static final int HOUR = 60 * 60;

    private static final int DAY = 60 * 60 * 24;

    public static String ttl(long ttl) {
        long start = System.currentTimeMillis();
        int time = (int) ((ttl - start) / 1000);
        if (time <= MINUTE) {
            return time + " 秒";
        } else if (time <= HOUR) {
            int minute = time / MINUTE;
            int second = time - (minute * MINUTE);
            return minute + "分" + second + "秒";
        } else if (time <= DAY) {
            int hour = time / HOUR;
            int minute = (time - hour * HOUR) / MINUTE;
            int second = (time - hour * HOUR - minute * MINUTE);
            return hour + "小时" + minute + "分" + second + "秒";
        } else{
            int day = time / DAY;
            int hour = (time - day * DAY) / HOUR;
            int minute = (time - day * DAY - hour * HOUR ) / MINUTE;
            int second = time - day * DAY * hour * HOUR - minute * MINUTE;
            return day + "天" + hour + "小时" + minute + "分" + second + "秒";
        }

    }

}

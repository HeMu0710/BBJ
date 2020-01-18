package com.qracker.bbj.model.tool;

/**
 * @program: BBJ
 * @description: 从规范化字符串读取数据
 * @author: HeMu-qracker
 * @create: 2020-01-16 23:48
 **/
public class Read {
    public static int readYear(String date) {
        /**
        * @Description: 传参举例 2020/1/17
        * @Param: [date]
        * @return: int
        * @Author: HeMu-qracker
        * @Date: 2020/1/17
        */
        return Integer.valueOf(date.substring(0, 4));
    }

    public static int readMonth(String date) {
        int n;
        String monthAndDay = date.substring(5);
        n = 0;
        while(monthAndDay.charAt(n) != '/')
            n ++;
        return Integer.valueOf(monthAndDay.substring(0, n));
    }

    public static int readDay(String date) {
        int n;
        String monthAndDay = date.substring(5);
        n = monthAndDay.length() - 1;
        while (monthAndDay.charAt(n) != '/')
            n --;
        return Integer.valueOf(monthAndDay.substring(n + 1));
    }

    public static int readHour(String time) {
        /**
        * @Description: 传参实例 23:24
        * @Param: [time]
        * @return: int
        * @Author: HeMu-qracker
        * @Date: 2020/1/18
        */
        int n = 0;
        while(time.charAt(n) != ':')
            n ++;
        return Integer.valueOf(time.substring(0, n));
    }

    public static int readMinute(String time) {
        int n = time.length() - 1;
        while(time.charAt(n) != ':')
            n --;
        return Integer.valueOf(time.substring(n + 1));
    }
}

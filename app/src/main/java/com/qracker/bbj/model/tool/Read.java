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

}

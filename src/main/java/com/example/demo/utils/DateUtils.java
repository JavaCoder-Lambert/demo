package com.example.demo.utils;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: lzj
 * @Date: 2023/3/13 23:14
 * @Description:
 */
public class DateUtils {

    public static final String TYPE="yyyy-MM-DD";

    public static LocalDateTime getTimeByStr(String value){
        LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(TYPE));
        return localDate.atStartOfDay();
        //// 一开始我的时间是  2019-01-01 00:00:00
        //		//1.定义格式
        //		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //
        //		//2.把字符串转成localDate
        //		LocalDate localDate = LocalDateTime.parse("2019-01-01 00:00:00", df).toLocalDate();
        //
        //		//3.定义格式
        //		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        //
        //		//4.把 2019-01-01  转成  2019/01/01
        //		String format = localDate.format(dtf);
        //		System.out.println(format);
        //————————————————
        //版权声明：本文为CSDN博主「是小方啦」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/weixin_42633131/article/details/89138069
    }

    public static void main(String[] args) {
        System.out.println(getTimeByStr("2023-1-1"));
    }



}

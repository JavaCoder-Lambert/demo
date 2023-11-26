package com.example.demo.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @Author: lzj
 * @Date: 2023/3/13 23:14
 * @Description:
 */
public class DateUtils {

    public static final String TYPE="yyyy-MM-DD";

    public static LocalDateTime getTimeByStr(String value){
        		//1.定义格式
        		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        		//2.把字符串转成localDate
        //        		//3.定义格式
//        		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(value, df).atStartOfDay();
    }

    public static void main(String[] args) {
        System.out.println(getTimeByStr("2023-1-1"));
    }



}

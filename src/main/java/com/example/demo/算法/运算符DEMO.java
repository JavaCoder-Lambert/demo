package com.example.demo.算法;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * x<<y 相当于 x*（2的y次方） ；x>>y相当于x/2y
 *     从计算速度上讲，移位运算要比算术运算快。
 * @Author: lzj
 * @Date: 2021/10/24 22:48
 * @Description:
 */
public class 运算符DEMO {

    public static int pow(int m,int n){
        int sum=1;
        int tmp=m;
        while (n!=0){
            if((n&1)==1){
                sum *=tmp;
            }
            tmp*=tmp;
            n=n>>>1;
        }
        return sum;
    }

    public static void main(String[] args) {
//         System.out.println(4<<5);
//        System.out.println(4>>5);
        //System.out.println(pow(3,3));
        final LocalDateTime fromDate = LocalDateTime.now();
        final LocalDateTime toDate = LocalDateTime.now().plusMinutes(10);
        if(Duration.between(fromDate,toDate).toMinutes()<60){
            System.out.println("1小时");
        }
        String value=DateUtil.formatBetween(ChronoUnit.MILLIS.between(fromDate, toDate), BetweenFormater.Level.HOUR);
        System.out.println(value);
        BigDecimal values=BigDecimal.valueOf(25).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        System.out.println(values);
    }
}

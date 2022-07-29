package com.example.demo.杂七杂八;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年03月24日 10:00
 */
@Slf4j
public class XueSingleTon {
    public static  int m=0;
    private static XueSingleTon instance=new XueSingleTon();
    public static int x=0;
    public static int y;



    private XueSingleTon(){
        m++;
        log.error("第{}次执行",m);
        x++;
        y++;
    }

    public static XueSingleTon getInstance(){
        m++;
        log.error("第{}次执行",m);
        return instance;
    }

    public static void main(String[] args) {
        XueSingleTon xueSingleTon=getInstance();
        System.out.println(x);
        System.out.println(y);
    }
}

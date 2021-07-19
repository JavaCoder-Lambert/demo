package com.example.demo.设计模式;

/**
 * @Author: lzj
 * @Date: 2021/7/19 23:45
 * @Description:
 */
public class Singleton {
    private Singleton() {

    }
    /**
     * 获取单例
     *
     * @return
     */
    public static Singleton getInstance() {
        return SingletonHolder.intstance;
    }
    /**
     * 一个私有的静态内部类，用于初始化一个静态final实例
     */
    private static class SingletonHolder {
        private static final Singleton intstance = new Singleton();
    }
}

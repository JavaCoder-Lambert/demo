package com.example.demo.设计模式;

import java.util.stream.IntStream;

/**
 * @Author: lzj
 * @Date: 2021/7/19 23:53
 * @Description:
 */
public class SingletonDemo3 {
    private SingletonDemo3() {

    }

    private enum Instance {
        /**
         * 实例对象
         */
        INSTANCE;

        private final SingletonDemo3 instance;

        /**
         *  enum构造函数只会创建一次.
         */
        Instance() {
            instance = new SingletonDemo3();
        }

        public SingletonDemo3 getInstance() {
            return instance;
        }
    }

    public static SingletonDemo3 getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.range(0, 100).forEach((item) -> {
            Thread thread = new Thread(() -> {
                System.out.println(SingletonDemo3.getInstance());
            });
            thread.start();
        });
    }

}

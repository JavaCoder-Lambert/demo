package com.example.demo.设计模式;

/**
 * @Author: lzj
 * @Date: 2021/9/20 22:25
 * @Description:
 */
public class ConsoleLogger extends AbstractLogger{

    public ConsoleLogger(int level) {
        this.level=level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}

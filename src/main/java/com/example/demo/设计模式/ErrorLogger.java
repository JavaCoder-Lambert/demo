package com.example.demo.设计模式;

/**
 * @Author: lzj
 * @Date: 2021/9/20 22:29
 * @Description:
 */
public class ErrorLogger extends AbstractLogger{

    public ErrorLogger(int level){
        this.level = level;
    }

    /**
     * 虚拟信息
     *
     * @param message 消息
     */
    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}

package com.example.demo.设计模式;

import lombok.Getter;
import lombok.Setter;

/**
 * 责任链模式
 * @Author: lzj
 * @Date: 2021/9/20 22:09
 * @Description:
 */
public abstract class AbstractLogger {
    public static int INFO=1;
    public static int DEBUG=2;
    public static int ERROR=3;

    protected  int level;

    @Getter
    @Setter
    protected AbstractLogger nextLogger;

    public void logMessage(int level,String message){
        if(this.level<=level){
            write(message);
        }
        if(nextLogger !=null){
            nextLogger.logMessage(level, message);
        }
    }

    /**
     * 虚拟信息
     * @param message 消息
     */
    abstract protected void write(String message);
}

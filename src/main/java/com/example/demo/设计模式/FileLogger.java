package com.example.demo.设计模式;

/**
 * @Author: lzj
 * @Date: 2021/9/20 22:31
 * @Description:
 */
public class FileLogger extends AbstractLogger{
    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}

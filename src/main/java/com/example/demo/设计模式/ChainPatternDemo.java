package com.example.demo.设计模式;

/**
 * @Author: lzj
 * @Date: 2021/9/20 22:31
 * @Description:
 */
public class ChainPatternDemo {

    private static AbstractLogger getChainOfLoggers(){
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();
        loggerChain.logMessage(AbstractLogger.INFO, "普通信息输出.");

        loggerChain.logMessage(AbstractLogger.DEBUG,
                "debug日志输出.");

        loggerChain.logMessage(AbstractLogger.ERROR,
                "错误日志输出.");

    }
}

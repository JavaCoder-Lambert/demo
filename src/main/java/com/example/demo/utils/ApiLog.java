package com.example.demo.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Henry
 * @create 2020-03-24 18:25
 */

public class ApiLog {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApiLog.class);

    public static boolean isDebugEnabled() {
        return LOGGER.isDebugEnabled();
    }

    public static boolean isInfoEnabled() {
        return LOGGER.isInfoEnabled();
    }

    public static boolean isErrorEnabled() {
        return LOGGER.isErrorEnabled();
    }

    public static boolean isWarnEnabled() {
        return LOGGER.isWarnEnabled();
    }

    public static boolean isTraceEnabled() {
        return LOGGER.isTraceEnabled();
    }

    public static void debug(String msg, Object... params) {
        LOGGER.debug(msg, params);
    }

    public static void info(String msg, Object... params) {
        LOGGER.info(msg, params);
    }

    public static void warn(String msg, Object... params) {
        LOGGER.warn(msg, params);
    }

    public static void error(String msg, Throwable throwable) {
        LOGGER.error(msg, throwable);
    }

    public static void error(String msg, Throwable throwable, Object... params) {
        LOGGER.error(msg, params);
        LOGGER.error("", throwable);
    }

    public static void error(String msg, Object... params) {
        LOGGER.error(msg, params);
    }

    public static void error(Throwable throwable) {
        LOGGER.error("", throwable);
    }
}

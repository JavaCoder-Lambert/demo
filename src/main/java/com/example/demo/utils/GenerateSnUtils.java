package com.example.demo.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GenerateSnUtils {

    private static final SimpleDateFormat SHORT_YEAR_TO_SECOND_FORMAT = new SimpleDateFormat("yyMMddHHmmssSSS");//15
    private static final SimpleDateFormat FULL_YEAR_TO_SECOND_FORMAT = new SimpleDateFormat("yyyyMMdd");//8
    private static final char[] NUMBERS_AND_LETTERS = ("0123456789abcdefghijklmnopqrstuvwxyz0123456789QWERTYUIOPASDFGHJKLZXCVBNM").toCharArray();
    private static final char[] NUMBERS = ("0123456789").toCharArray();

    public static synchronized String generateSnByTimeStrAndRandomStrLen(int randomStrLen) {
        return FULL_YEAR_TO_SECOND_FORMAT.format(new Date()) + createRandomString(randomStrLen);
    }

    public static synchronized String generateSnByShortYearTimeStrAndRandomStrLen(int randomStrLen) {
        return SHORT_YEAR_TO_SECOND_FORMAT.format(new Date()) + createRandomString(randomStrLen);
    }

    public synchronized static String createRandomNum(int length) {
        if (length < 1) {
            return "";
        }
        int nextIntMax = NUMBERS.length - 1;
        Random random = new Random();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = NUMBERS[random.nextInt(nextIntMax)];
        }
        return new String(randBuffer);
    }

    /**
     * 获取指定位数的随机字符串
     *
     * @param length 位数
     * @return 随机字符串
     */
    public synchronized static String createRandomString(int length) {
        if (length < 1) {
            return "";
        }
        int nextIntMax = NUMBERS_AND_LETTERS.length - 1;
        Random random = new Random();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = NUMBERS_AND_LETTERS[random.nextInt(nextIntMax)];
        }
        return new String(randBuffer);
    }


}

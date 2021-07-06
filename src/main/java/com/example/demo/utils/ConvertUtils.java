package com.example.demo.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * 单位换算工具类，保留2位小数/版本判断转换类
 */
public class ConvertUtils {

    private ConvertUtils() {
    }

    /**
     * 保留2位小数
     *
     * @return
     */
    public static double transferData(double data) {
        BigDecimal bg = new BigDecimal(data);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

	/**
	 * 保留指定位数位小数
	 *
	 * @param data 初始数据
	 * @param num  小数位数
	 * @return
	 */
	public static double transferData(double data, int num) {
		BigDecimal bg = new BigDecimal(data);
		return bg.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


    public static double meterToMile(double length) {
        return new BigDecimal(length).divide(new BigDecimal(1609.344), 2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double meterToKilometer(double length) {
        return new BigDecimal(length).divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP).doubleValue();
    }

    public static String mapToString(Map<String, ?> map, String delimiter) {
        return map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(delimiter));
    }

    public static Map<String, String> stringToMap(String string, String delimiter) {
        return Arrays.stream(string.split(delimiter))
                .map(entry -> entry.split("=", 2))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
    }

    /**
     * 版本号转换对比类
     * 2020年4月9号 和穆青约定号 版本号提供的都是类似2.7.9 2.8.0 这种版本号 因此讲版本号替换为数据判断后即可
     * true 代表当前版本号大于配置好的
     * false 代表当前版本号小于配置好的
     * replaceAll必须带[] 因为他是一个regex 即正则
     *
     * @param version1 当前版本号
     * @param version2 常量配置的版本号
     * @return
     */
    @Deprecated
    public static boolean versionCompare(String version1, String version2) {
        int num1 = Integer.parseInt(version1.replaceAll(Matcher.quoteReplacement("[.]"), Matcher.quoteReplacement("")));
        int num2 = Integer.parseInt(version2.replaceAll(Matcher.quoteReplacement("[.]"), Matcher.quoteReplacement("")));
        return num1 > num2;
    }

	/**
	 * 根据经纬度小数点来进行判断 如果小数点后不足5位 则直接返回 若充足则返回截取到小数点后5位的数字
	 * @param value 带.的字符串
	 * @return
	 */
	public static String getStringValue(String value){
		String[] string1=value.split("\\.");
		int num=value.indexOf(".");
		if(string1[1].length()> 5){
			return value.substring(0, num+6);
		}
		return value;
	}

    public static void main(String[] args) {
        System.out.println("1.1.1".replaceAll("[.]", ""));
        System.out.println(versionCompare("2.8.8", "2.7.9"));
    }

}

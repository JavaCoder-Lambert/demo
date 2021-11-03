package com.example.demo.杂七杂八;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年11月03日 14:33
 */
public class Demo {
    public static boolean isNumeric(final String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }
    public static void main(String[] args) {
        String locationCode="1_1_1_1";
        int num =locationCode.length();
        String value1=locationCode.substring(0,num-1);
        String value2=locationCode.substring(num-1,num);
        if(isNumeric(value2)){
            System.out.println("是数字");
        }
        System.out.println(value1);
        System.out.println(value2);
    }
}

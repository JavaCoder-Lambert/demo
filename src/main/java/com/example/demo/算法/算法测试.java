package com.example.demo.算法;

import cn.hutool.core.lang.UUID;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 查找唯一数字
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年12月15日 15:40
 */
public class 算法测试 {

    public static void main(String[] args) {
        int[] array = { 3, 2, 1, 1, 3 };
        int number1 = 算法测试.getNumber1(array);
        System.out.println(number1);
        System.out.println("-------------------");
        int number2 = 算法测试.getNumber2(array);
        System.out.println(number2);
        System.out.println("-------------------");
        int number3 = 算法测试.getNumber3(array);
        System.out.println(number3);

    }

    // 方法1：
    public static int getNumber1(int nums[]) {
        /*
         * 解决思路： 先对数组进行排序 遍历数组，比较相邻的个元素 只要一发现和相邻的元素不相等就返回这个数值
         */
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && nums[i] != nums[i + 1]) {
                return nums[i];
            } else if (i > 0 && i < nums.length - 1 && nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
                return nums[i];
            } else if (i == nums.length - 1 && nums[i] != nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // 方法2
    public static int getNumber2(int nums[]) {
        /*
         * 解决思路： 根据HashSet集合的特点可以知，不能存放重复元素， 如果集合里已经存在重复元素result.add(nums[i])的值为false;
         * 因为集合里只有一个元素，所以利用iterator().next()可以找出集合中唯一的元素
         */
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!result.add(nums[i])) {
                result.remove(nums[i]);
            }
        }
        return result.iterator().next();

    }

    // 方法3
    public static int getNumber3(int nums[]) {
        /*
         * 解决思路： a ^ 0 = a; a ^ a = 0; 受到异或运算的启发可解决该问题
         * 异或
         */
        int a = 0, i;
        for (i = 0; i < nums.length; ++i) {
            a = a ^ nums[i];
        }
        return a;
    }
}

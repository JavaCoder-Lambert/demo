package com.example.demo.算法;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年10月13日 14:47
 */
public class erfensolution {

    /**
     * 只是适用于有序数组
     */
    public static class Solution{
        public static int search(int []array, int k){
            if(array == null || array.length == 0) {
                return 0;
            }
            if(k < array[0]) {
                return 0;
            }
            int first = binarySearch(array, k);
            int last = binarySearch(array, k + 1);
            return (first == array.length || array[first] != k)?0:last-first;
        }
        public static int binarySearch(int[] nums, int k){
            int left = 0, right = nums.length;
            while(left < right){
                int mid = left + (right - left)/2;
                if(nums[mid] >= k)
                    right = mid ;
                else
                    left = mid + 1;
            }
            return left;
        }
    }


    /**
     * 制定在一个数组里面查找到制定数据信息
     * 2121萨达测试哦信息中河大厦这就是恩爱21 打sasassa
     * java 测试方法
     * @param args
     */
    public static void main(String[] args) {
        int[] nums={1,2,3,4,56};
        int target=4;
        int value=Solution.search(nums,target);
        System.out.println(value);
    }
}

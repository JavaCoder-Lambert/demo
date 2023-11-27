package com.example.demo.算法;

import com.example.demo.utils.CollectionUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: lzj
 * @Date: 2023/11/26 21:22
 * @Description:
 */
public class Dota2 {

    public static void main(String[] args) {
        System.out.println(predictDota2("RDDRRDDD"));
    }

    public static String predictDota2(String value) {
        int length = value.length();
        Queue<Integer> r = new LinkedList<>();
        Queue<Integer> d = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            if (value.charAt(i) == 'R') {
                r.offer(i);
            } else {
                d.offer(i);
            }
        }
        while (!r.isEmpty() && !d.isEmpty()) {
            int rp = r.poll(), dp = d.poll();
            if (rp < dp) {
                r.offer(rp + length);
            } else {
                d.offer(dp + length);
            }
        }

        return d.isEmpty() ? "R" : "D";

    }
}

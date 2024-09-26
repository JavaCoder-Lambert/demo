package com.example.demo.算法;

import org.aspectj.weaver.ast.Var;
import smile.math.distance.Distance;
import smile.math.distance.DynamicTimeWarping;
import smile.math.distance.EuclideanDistance;

/**
 * @Author: lzj
 * @Date: 2024/9/2 15:37
 * @Version: 1.0
 * @Description:
 */
public class DTWDemo {

    public static void main(String[] args) {
        // 定义两个时间序列
        double[] series1 = {1,3,4,9,8,2,1,5,7,3};
        double[] series2 = {1,3,1,4};

        // 创建一个DTW实例，使用欧几里得距离作为距离度量
        Distance dtw = null;
        DynamicTimeWarping<Object> DynamicTimeWarping=new DynamicTimeWarping<>(null);


        // 计算两个时间序列之间的DTW距离
        double distance = DynamicTimeWarping.d(series1, series2);

        // 输出DTW距离
        System.out.println("DTW distance between series1 and series2: " + distance);


       // DynamicTimeWarping<Distance> DynamicTimeWarping1=new DynamicTimeWarping<Distance>();
    }

}

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
        double[] series1 = {1, 2, 3, 4, 5};
        double[] series2 = {2, 3, 4, 5, 6, 7};

        // 创建一个欧几里得距离计算器
        EuclideanDistance euclideanDistance = new EuclideanDistance();

        // 创建一个DTW实例，使用欧几里得距离作为距离度量
        Distance dtw = (x, y) -> 0;
        DynamicTimeWarping DynamicTimeWarping=new DynamicTimeWarping(dtw);

        // 计算两个时间序列之间的DTW距离
        double distance = dtw.d(series1, series2);

        // 输出DTW距离
        System.out.println("DTW distance between series1 and series2: " + distance);
    }

}

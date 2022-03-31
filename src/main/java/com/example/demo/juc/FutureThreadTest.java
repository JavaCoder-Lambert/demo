package com.example.demo.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年03月31日 11:32
 */
public class FutureThreadTest {


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        Callable<KitchenWare> callable = new Callable<KitchenWare>() {
            public KitchenWare call() throws Exception {
                System.out.println("第一步: 网上下单");
                System.out.println("第一步: 等待厨具");
                try {
                    Thread.sleep(5000); // 等待厨具时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第一步: 快递送货");
                return new KitchenWare();
            }

        };
        // 包装为异步执行的对象
        FutureTask<KitchenWare> task = new FutureTask<>(callable);
        new Thread(task).start();

        // 买食材
        FoodMaterial fm = new FoodMaterial();
        Thread.sleep(2000);
        System.out.println("第二步: 食材已经到位");

        if (!task.isDone()) {
            System.out.println("厨具还没有到.....");
        }
        // 通过阻塞形式获取到异步块执行的结果
        KitchenWare kc = task.get(); // 阻塞
        // 烹饪美食
        cooking(kc, fm);
        System.out.println("第三步: 美食烹饪完成");
        long end = System.currentTimeMillis();
        System.out.println("烹饪美食时间为:" +  (end - start));
    }


    /**
     * 厨具类
     * @author Administrator
     *
     */
    static class KitchenWare {

    }

    /**
     * 食材类
     * @author Administrator
     *
     */
    static class FoodMaterial {

    }

    /**
     * 定义烹饪食物的方法
     * @param kc
     * @param fm
     */
    static void cooking(KitchenWare kc, FoodMaterial fm) {

    }
}

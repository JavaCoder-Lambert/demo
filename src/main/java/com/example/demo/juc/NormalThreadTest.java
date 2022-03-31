package com.example.demo.juc;


/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年03月31日 11:21
 */
public class NormalThreadTest {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        // 开启购买厨具线程
        ShoppingThread shopping = new ShoppingThread();
        shopping.start();
        shopping.join(); // 保障厨具购买并送货
        // 获取到购买厨具
        KitchenWare kc = shopping.kc;

        // 买食材
        FoodMaterial fm = new FoodMaterial();
        Thread.sleep(2000);
        System.out.println("第二步: 食材已经到位");
        // 烹饪美食
        cooking(kc, fm);
        System.out.println("第三步: 美食烹饪完成");
        long end = System.currentTimeMillis();
        System.out.println("烹饪美食时间为:" +  (end - start));
    }


    /**
     * 定义网上购物厨具线程
     * @author Administrator
     *
     */
    static class ShoppingThread extends Thread {

        // 厨具对象引用
        private KitchenWare kc;

        @Override
        public void run() {
            System.out.println("第一步: 网上下单");
            System.out.println("第一步: 等待厨具");
            try {
                Thread.sleep(5000); // 等待厨具时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步: 快递送货");
            // 生产厨具
            kc = new KitchenWare();
        }
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


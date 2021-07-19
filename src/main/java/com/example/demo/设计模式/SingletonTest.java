package com.example.demo.设计模式;

/**
 * 单例模式分为懒汉模式和饿汉模式
 *
 * @Author: lzj
 * @Date: 2021/7/19 23:28
 * @Description:
 */
public class SingletonTest {

    /**
     * 饿汉模式
     */
    public static class HungrySingle {
        /**
         * 饿汉模式，不管hungrySingle对象是否有使用到，都会先创建出来
         * 由于饿汉模式在对象使用之前就已经被创建，所以是不会存在线程安全问题
         */
        private static HungrySingle hungrySingle = new HungrySingle();

        /**
         * 私有化构造器，禁止外部创建
         */
        private HungrySingle() {
        }

        /**
         * 提供获取实例的方法
         */
        public static HungrySingle getInstance() {
            return hungrySingle;
        }
    }


    /**
     * 懒汉模式
     * <>
     * JVM在运行时，在保证单线程最终结果不会受影响的情况下，对指令进行优化，就有可能对指令进行重排序，同样会破坏单例。
     * <p>
     * lazySingle = new LazySingle();
     * //这样一段代码在运行时会生成3条指令，即： 1\. 分配内存空间 2\. 创建对象 3\. 指向引用
     * //正常情况下是会按照1 2 3顺序执行，但JVM优化器进行指令重排后，则可能变为：1\. 分配内存空间 3\. 指向引用  2\. 创建对象
     * //在单线程下，这样的优化没有问题，但是多线程下，线程是在争抢CPU时间碎片的。假设A刚刚执行完 1 3 //条指令，此时B争抢到时间碎片，发现对象不为空了，就直接返回，
     * 但此时对象还没有真正被创建。B调用
     * //此对象就会抛出异常
     * //而volatile关键字修饰的变量可以禁止指令重排序，则可以保证指令会是1 2 3顺序执行。
     * //加上volatile修饰
     * private volatile  static LazySingle lazySingle = null;
     * </>
     */
    public static class LazySingle {
        /**
         * 懒汉模式，不会先创建对象，而是在调用的时候才会创建对象
         */
        private static volatile LazySingle lazySingle = null;

        private LazySingle() {
        }

        /**
         * 调用的时候创建对象并返回
         */
        public static LazySingle getInstance() {
            //first check
            if (lazySingle == null) {
                synchronized (LazySingle.class) {
                    //double check
                    if (lazySingle == null) {
                        lazySingle = new LazySingle();
                    }
                }
            }
            return lazySingle;
        }
    }
}
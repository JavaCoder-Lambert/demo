package com.example.demo.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lzj
 * @Date: 2021/10/26 22:09
 * @Description:
 */
public class 綫程池 {
//    private static AtomicInteger total=new AtomicInteger(0);
    private static int total=0;
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 5000,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5)
            , Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    ThreadLocal<Integer> tl0 = new ThreadLocal<Integer>();
    ThreadLocal<Double> tl1 = new ThreadLocal<Double>();
    ThreadLocal<Long> tl2 = new ThreadLocal<Long>();

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("线程:" + Thread.currentThread().getName());
            Integer num = DySchedule.getLine();
            System.out.println("startline = " +(num-1000)+",endline = " + num);
        }
    }

    public static class DySchedule {
        private static AtomicInteger line = new AtomicInteger(0);
        static ExecutorService pool = Executors.newFixedThreadPool(100);

        public static int getLine(){
            return line.addAndGet(1000);
        }
        public static void doJob(){
            for (int i = 0;i<100;i++){
                Thread thread = new MyThread();
                pool.execute(thread);
            }
            pool.shutdown();

        }
        public static void main(String[] args) {
            DySchedule.doJob();
        }
    }

    /*public static void main(String[] args) throws InterruptedException *//*{
        //生成计数器 且默认值为1 即一个线程使用一次后则释放当前线程
        CountDownLatch downLatch = new CountDownLatch(1);
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    //await 就是加入阻塞队列
                    downLatch.await();
                    for(int j = 0; j < 1000; j++){
                        total.addAndGet(1);
                    }
                    System.out.println("当前线程名称"+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("当前睡眠中的线程名称"+Thread.currentThread().getName());
        Thread.sleep(2000);
        //对计数器进行递减1操作，当计数器递减至0时，当前线程会去唤醒阻塞队列里的所有线程。
        downLatch.countDown();
        Thread.sleep(2000);
        System.out.println(total);
    }
*/


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(1);
        ReentrantLock lock = new ReentrantLock();
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    downLatch.await();
                    lock.lock(); // 加锁
                    for(int j = 0; j < 1000; j++){
                        total++;
                    }
                    System.out.println("解锁前当前线程名称"+Thread.currentThread().getName());
                    lock.unlock(); // 解锁
                    System.out.println("解锁后当前线程名称"+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("当前睡眠中的线程名称"+Thread.currentThread().getName());
        Thread.sleep(2000);
        //释放线程后才会输出信息。因为这个线程被锁住了 因此会先输出当前睡眠中的线程名称信息之后释放后在输出上面的信息
        downLatch.countDown();
        Thread.sleep(2000);
        System.out.println("当前睡眠后的线程名称"+Thread.currentThread().getName());
        System.out.println(total);
    }

}

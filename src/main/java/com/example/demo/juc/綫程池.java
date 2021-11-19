package com.example.demo.juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lzj
 * @Date: 2021/10/26 22:09
 * @Description:
 */
public class 綫程池 {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 5000,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5)
            , Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

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

    public static void main(String[] args) {

    }

}

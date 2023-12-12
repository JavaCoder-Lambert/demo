package com.example.demo.juc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 線程缓存相关
 * @Author: lzj
 * @Date: 2023/12/12 23:25
 * @Description:
 */
public class ThreadLocalRef {


//        public final static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
//        public static final int CORE_THREAD_SIZE = 2;
//
//        public static ThreadPoolExecutor executorService = new ThreadPoolExecutor(CORE_THREAD_SIZE,
//                CORE_THREAD_SIZE,
//                10,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>());
//
//        public static void main(String[] args) throws InterruptedException {
//
//            inheritableThreadLocal.set("main thread-1");
//            executorService.submit(() -> {
//                System.out.println("1 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get());
//            });
//
//            inheritableThreadLocal.set("main thread-2");
//            executorService.submit(() -> {
//                System.out.println("2 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get());
//            });
//
//            inheritableThreadLocal.set("main thread-3");
//            executorService.submit(() -> {
//                System.out.println("3 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get());
//            });
//
//            inheritableThreadLocal.set("main thread-4");
//            executorService.submit(() -> {
//                System.out.println("4 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get());
//            });
//
//        }

    public final static TransmittableThreadLocal<String> inheritableThreadLocal = new TransmittableThreadLocal<>();
    public static final int CORE_THREAD_SIZE = 2;

    public static ExecutorService executorService =
            TtlExecutors.getTtlExecutorService(new ThreadPoolExecutor(CORE_THREAD_SIZE,
                    CORE_THREAD_SIZE,
                    10,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>())
            );

    public static void main(String[] args) throws InterruptedException {

        inheritableThreadLocal.set("main thread-1");
        executorService.submit(
                () -> System.out.println("1 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get())
        );

        inheritableThreadLocal.set("main thread-2");
        executorService.submit(
                () -> System.out.println("2 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get())
        );

        inheritableThreadLocal.set("main thread-3");
        executorService.submit(
                () -> System.out.println("3 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get())
        );

        inheritableThreadLocal.set("main thread-4");
        executorService.submit(
                () -> System.out.println("4 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get())
        );

    }

}

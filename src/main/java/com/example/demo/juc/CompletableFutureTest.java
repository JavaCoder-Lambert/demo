package com.example.demo.juc;

import java.util.concurrent.CompletableFuture;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年03月31日 11:35
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        //supplyAsync内部使用ForkJoinPool线程池执行任务
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "100";
        }).whenComplete((v, r) -> {
            System.out.println("计算结果是: " + v);
        });
        //CompletableFuture里使用的线程池里的线程默认是daemon的。main线程结束后，整个程序也
        //结束了，这里将main线程join后任务里的代码才可以执行完
        Thread.currentThread().join();
    }
}

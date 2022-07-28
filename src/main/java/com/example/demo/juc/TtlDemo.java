package com.example.demo.juc;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年07月28日 16:43
 */
public class TtlDemo {
    private static ThreadLocal<String> TTL = new TransmittableThreadLocal<>();
    //private static ThreadLocal<String> TTL = new ThreadLocal<>();
    //private static ThreadLocal<String> TTL = new InheritableThreadLocal<>();
    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            // 在父线程中设置变量
            TTL.set("throwable");
            new Thread(TtlDemo::methodFrame1).start();
            try {
                TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void methodFrame1() {
        methodFrame2();
    }

    private static void methodFrame2() {
        System.out.println(TTL.get());
    }
}

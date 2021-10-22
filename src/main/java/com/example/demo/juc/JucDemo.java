package com.example.demo.juc;

import java.util.concurrent.CountDownLatch;

/**
 * <>
 * 可以看到子线程并没有因为调用latch.countDown而阻塞,会继续进行该做的工作,只是通知计数器-1,
 * 即完成了我们如上说的场景,只需要在所有进程都进行到某一节点后才会执行被阻塞的进程.
 * 如果我们想要多个线程在同一时间进行就要用到CyclicBarrier了</>
 *
 * @Author: lzj
 * @Date: 2021/10/22 23:18
 * @Description:
 */
public class JucDemo {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            //lambda中只能只用final的变量
            final int times = i;
            new Thread(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在赶路");
                    Thread.sleep(1000 * times);
                    System.out.println("子线程" + Thread.currentThread().getName() + "到公司了");
                    //调用latch的countDown方法使计数器-1
                    latch.countDown();
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始工作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        try {
            System.out.println("门卫等待员工上班中...");
            //主线程阻塞等待计数器归零
            latch.await();
            System.out.println("员工都来了,门卫去休息了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

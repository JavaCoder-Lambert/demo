package com.example.demo.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <>
 * 可以看到所有线程在其他线程没有准备好之前都在被阻塞中,等到所有线程都准备好了才继续执行 我们在创建CyclicBarrier对象时传入了一个方法,
 * 当调用CyclicBarrier的await方法后,当前线程会被阻塞等到所有线程都调用了await方法后 调用传入CyclicBarrier的方法,然后让所有的被阻塞的线程一起运行
 * </>
 *
 * @Author: lzj
 * @Date: 2021/10/22 23:18
 * @Description:
 */
public class JucDemo2 {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("所有人都准备好了裁判开始了"));
        for (int i = 0; i < 10; i++) {
            //lambda中只能只用final的变量
            final int times = i;
            new Thread(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在准备");
                    Thread.sleep(1000 * times);
                    System.out.println("子线程" + Thread.currentThread().getName() + "准备好了");
                    cyclicBarrier.await();
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始跑了");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

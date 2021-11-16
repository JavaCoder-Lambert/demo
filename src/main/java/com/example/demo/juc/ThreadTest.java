package com.example.demo.juc;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年11月16日 13:50
 */
public class ThreadTest {

     static class MyThread extends Thread{
         @Override
         public void run(){
             for (int i = 0; i < 10; i++) {
                 System.out.println(Thread.currentThread().getName()+"正在运行");
             }
         }
     }

     static class MyThread1 implements Runnable{

         /**
          * When an object implementing interface <code>Runnable</code> is used
          * to create a thread, starting the thread causes the object's
          * <code>run</code> method to be called in that separately executing
          * thread.
          * <p>
          * The general contract of the method <code>run</code> is that it may
          * take any action whatsoever.
          *
          * @see Thread#run()
          */
         @Override
         public void run() {
             for (int i = 0; i < 10; i++) {
                 System.out.println(Thread.currentThread().getName()+"MyThread1方法中的线程正在运行中");
             }
         }
     }

    public static void main(String[] args) {
/*        MyThread myThread = new MyThread();
        myThread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "在运行！");
        }*/
        MyThread1 myThread1=new MyThread1();
        Thread t1=new Thread(myThread1);
        t1.start();
        for (int i = 0; i < 10; i++)
        {
            System.out.println(Thread.currentThread().getName() + "在运行！");
        }
    }


    /**
     * 线程状态
     *
     * 虚拟机中的线程状态有六种，定义在Thread.State中：
     *
     * 1、新建状态NEW
     *
     * new了但是没有启动的线程的状态。比如"Thread t = new Thread()"，t就是一个处于NEW状态的线程
     *
     * 2、可运行状态RUNNABLE
     *
     * new出来线程，调用start()方法即处于RUNNABLE状态了。处于RUNNABLE状态的线程可能正在Java虚拟机中运行，也可能正在等待处理器的资源，因为一个线程必须获得CPU的资源后，才可以运行其run()方法中的内容，否则排队等待
     *
     * 3、阻塞BLOCKED
     *
     * 如果某一线程正在等待监视器锁，以便进入一个同步的块/方法，那么这个线程的状态就是阻塞BLOCKED
     *
     * 4、等待WAITING
     *
     * 某一线程因为调用不带超时的Object的wait()方法、不带超时的Thread的join()方法、LockSupport的park()方法，就会处于等待WAITING状态
     *
     * 5、超时等待TIMED_WAITING
     *
     * 某一线程因为调用带有指定正等待时间的Object的wait()方法、Thread的join()方法、Thread的sleep()方法、LockSupport的parkNanos()方法、LockSupport的parkUntil()方法，就会处于超时等待TIMED_WAITING状态
     *
     * 6、终止状态TERMINATED
     *
     * 线程调用终止或者run()方法执行结束后，线程即处于终止状态。处于终止状态的线程不具备继续运行的能力
     */
}

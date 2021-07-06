package com.example.demo.common.lockLearn;

import lombok.SneakyThrows;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * 死锁demo
 * <p>
 * 什么是死锁 死锁就是互相等待对方释放 一直无法拿到对方的信息 只有拿到才执行下一步 但是一直无法拿到
 * </p>
 *
 * @Author: lzj
 * @Date: 2021/3/24 23:37
 * @Description:
 */
public class DeadLock {

    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        new DeadLock().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (A) {
                    System.out.println("A的锁");
                    synchronized (B){
                        System.out.println("B的锁");
                    }
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    System.out.println("B的第二个锁");
                    synchronized (A){
                        System.out.println("A的第二个锁");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

/**
 *好看的单例模式
 */
public static class Singleton {
    private Singleton() {
    }

    private volatile static Singleton instance;

    public static Singleton getInstance() {
        if (null == instance) {
            synchronized (Singleton.class) {
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}




}

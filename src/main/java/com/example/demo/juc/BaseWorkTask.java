package com.example.demo.juc;

/**
 * 抽象工作线程，若想进行多线程工作，可以继承此抽象类，再重写相关方法实现具体的业务逻辑
 *
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年07月04日 14:13
 */
public abstract class BaseWorkTask implements Runnable {
    //工作任务线程
    /*@Override
    public void run() {
        // 工作示例
        System.out.println(Thread.currentThread().getId() + " is start");
        process();
        System.out.println(Thread.currentThread().getId() + " is over");
    }*/

    /**
     * 业务处理方法
     *
     * @throws RuntimeException 业务处理异常
     */
    protected abstract void process() throws RuntimeException;
}

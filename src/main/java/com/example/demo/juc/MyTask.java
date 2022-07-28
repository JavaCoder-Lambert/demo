package com.example.demo.juc;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年07月04日 14:57
 */
public class MyTask implements Runnable {
    private final int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + "执行完毕");
    }
}

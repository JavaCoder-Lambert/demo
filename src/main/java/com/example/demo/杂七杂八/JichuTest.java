package com.example.demo.杂七杂八;

import com.example.demo.juc.CustomerThreadPool;
import com.example.demo.juc.MyTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年06月20日 17:57
 */
public class JichuTest {
    //    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
//        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//    }
    public static void main(String[] args) {
        ThreadPoolExecutor executor= CustomerThreadPool.createDefaultThreadPool();
        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}

package com.example.demo.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lzj
 * @Date: 2021/10/26 22:09
 * @Description:
 */
public class 綫程池 {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 5000,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5)
            , Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());



}

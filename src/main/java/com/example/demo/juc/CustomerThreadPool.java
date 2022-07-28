package com.example.demo.juc;

import java.util.concurrent.*;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年07月04日 13:43
 */
public class CustomerThreadPool {
    /**
     * 线程池核心线程数，即线程池中常驻的线程数量
     **/
    private static final int DEFAULT_CORE_POLL_SIZE = 8;

    /**
     * 线程池允许的最大线程数，非核心线程在超时之后会被清除，受限于 CAPACITY，需要根据实际的物理机配置去计算
     **/
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 1024;

    /**
     * 线程没有任务执行时可以保持的时间【非核心线程】
     **/
    private static final long DEFAULT_KEEP_ALIVE_TIME = 0;

    /**
     * keepAliveTime 的时间单位
     **/
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MICROSECONDS;

    /**
     * 阻塞队列的最大容量
     **/
    private static final Integer MAX_WORK_QUEUE_CAPACITY = 1024 * 10;

    /**
     * 任务阻塞队列，用于存储等待执行的任务，默认采用有界队列
     **/
    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE = new ArrayBlockingQueue<Runnable>(MAX_WORK_QUEUE_CAPACITY);

    /**
     * 线程工厂，用来创建线程，可自定义对线程的控制
     **/
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = Executors.defaultThreadFactory();

    /**
     * rejectHandler：当任务队列已满时，拒绝任务提交时的策略：
     * AbortPolicy【默认】：丢掉任务，并抛RejectedExecutionException异常。
     * DiscardPolicy：直接丢掉任务，不抛异常。
     * DiscardOldestPolicy：丢掉最老的任务，然后调用execute立刻执行该任务（新进来的任务）。
     * CallerRunsPolicy【推荐】：在调用者的当前线程去执行这个任务。
     */
    private static final RejectedExecutionHandler DEFAULT_HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     * 创建线程池
     *
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   没有任务执行时非核心线程可以保持的时间
     * @param unit            keepAliveTime 的时间单位
     * @param workQueue       任务阻塞队列，用于存储等待执行的任务
     * @param threadFactory   线程工厂
     * @param handler         当任务队列已满时，拒绝任务提交时的策略
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPool(int corePoolSize,
                                                      int maximumPoolSize,
                                                      long keepAliveTime,
                                                      TimeUnit unit,
                                                      BlockingQueue<Runnable> workQueue,
                                                      ThreadFactory threadFactory,
                                                      RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory, handler);
    }

    /***
     * 创建线程池
     * @param corePoolSize 核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime 没有任务执行时非核心线程可以保持的时间
     * @param unit keepAliveTime 的时间单位
     * @param workQueue 任务阻塞队列，用于存储等待执行的任务
     * @param threadFactory 线程工厂
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPool(int corePoolSize,
                                                      int maximumPoolSize,
                                                      long keepAliveTime,
                                                      TimeUnit unit,
                                                      BlockingQueue<Runnable> workQueue,
                                                      ThreadFactory threadFactory) {
        return createThreadPool(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, DEFAULT_HANDLER);
    }

    /***
     * 创建线程池
     * @param corePoolSize 核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime 没有任务执行时非核心线程可以保持的时间
     * @param unit keepAliveTime 的时间单位
     * @param workQueue 任务阻塞队列，用于存储等待执行的任务
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPool(int corePoolSize,
                                                      int maximumPoolSize,
                                                      long keepAliveTime,
                                                      TimeUnit unit,
                                                      BlockingQueue<Runnable> workQueue) {
        return createThreadPool(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, DEFAULT_THREAD_FACTORY);
    }

    /***
     * 创建线程池
     * @param corePoolSize 核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime 没有任务执行时非核心线程可以保持的时间
     * @param unit keepAliveTime 的时间单位
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPool(int corePoolSize,
                                                      int maximumPoolSize,
                                                      long keepAliveTime,
                                                      TimeUnit unit) {
        return createThreadPool(corePoolSize, maximumPoolSize, keepAliveTime, unit, DEFAULT_WORK_QUEUE);
    }

    /**
     * 创建默认的线程池，配置如下：
     * corePoolSize = 8;
     * maximumPoolSize = 1024;
     * keepAliveTime = 0;
     * unit = TimeUnit.MICROSECONDS
     * workQueue = new ArrayBlockingQueue<Runnable>(10240);
     * threadFactory = Executors.defaultThreadFactory();
     * handler = new ThreadPoolExecutor.CallerRunsPolicy();
     */
    public static ThreadPoolExecutor createDefaultThreadPool() {
        return createThreadPool(DEFAULT_CORE_POLL_SIZE, DEFAULT_MAXIMUM_POOL_SIZE, DEFAULT_KEEP_ALIVE_TIME, DEFAULT_TIME_UNIT);
    }
}

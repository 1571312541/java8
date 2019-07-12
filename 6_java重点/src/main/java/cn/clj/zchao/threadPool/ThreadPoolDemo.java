package cn.clj.zchao.threadPool;

import java.util.concurrent.*;

/**
 * 〈〉
 *
 * @author zc
 * @create 2019/7/4
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        /**创建一个5个大小的线程池，即固定长度的线程池，可以控制线程最大并发数，超出的会在队列中等待，
         *其创建的corePoolSize和maximumPoolSize大小一样，使用LinkedBlockingQueue
         *
         *     public static ExecutorService newFixedThreadPool(int nThreads) {
         *         return new ThreadPoolExecutor(nThreads, nThreads,
         *                                       0L, TimeUnit.MILLISECONDS,
         *                                       new LinkedBlockingQueue<Runnable>());
         *     }
         *
         *   适合执行长期的任务
         */
        ExecutorService executor = Executors.newFixedThreadPool(5);

        /**
         * 创建一个单线程的线程池，只有一个线程工作，保证所有任务顺序执行
         * 其创建的corePoolSize和maximumPoolSize大小都是1，使用LinkedBlockingQueue
         *     public static ExecutorService newSingleThreadExecutor() {
         *         return new FinalizableDelegatedExecutorService
         *             (new ThreadPoolExecutor(1, 1,
         *                                     0L, TimeUnit.MILLISECONDS,
         *                                     new LinkedBlockingQueue<Runnable>()));
         *     }
         *  适合 一个任务一个任务的执行
         */
        ExecutorService executor2 = Executors.newSingleThreadExecutor();
        /**
         * 创建一个可缓存的线程池
         * 其创建的corePoolSize是0，maximumPoolSize是Integer.MAX_VALUE，空闲60s后销毁
         *  使用SynchronousQueue，来了任务就创建线程
         *
         *     public static ExecutorService newCachedThreadPool() {
         *         return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *                                       60L, TimeUnit.SECONDS,
         *                                       new SynchronousQueue<Runnable>());
         *     }
         *   适合 多个任务的执行
         */
        ExecutorService executor3 = Executors.newCachedThreadPool();


        for (int i = 0; i < 10; i++) {
            executor3.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }




    }


}

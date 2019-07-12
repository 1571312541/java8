package cn.clj.zchao.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 〈〉
 * 线程池
 * 线程池主要是控制运行线程的数量，处理过程中将任务放入队列，当线程创建后启动这些任务，
 * 如果线程数量超出了最大线程数量，超出的线程排队等候，等其他线程执行完毕，再从队列中去除任务执行
 *
 * 特点：
 * 线程复用，控制最大线程并发数，管理线程
 * 降低资源消耗，通过重复利用已创建的线程，避免线程创建和销毁的消耗
 * 提高相应速速，当任务到达时，任务不需要等待线程的创建就可以执行
 * 提高线程的管理性，线程是稀缺资源，如果无限制创建，会消耗系统资源，降低系统稳定性，使用线程池可以进行统一分配，调优和监控
 *
 * 常见线程池：
 * 1、Executors.newFixedThreadPool（Int num）；固定长度的线程池 其创建的corePoolSize和maximumPoolSize大小一样，使用LinkedBlockingQueue
 * 2、Executors.newSingleThreadExecutor()；单线程线程池，其创建的corePoolSize和maximumPoolSize大小是1，，使用LinkedBlockingQueue
 * 3、Executors.newCachedThreadPool(); 可缓存的线程池，其创建的corePoolSize是0，maximumPoolSize是Integer.MAX_VALUE，空闲60s后销毁 使用SynchronousQueue，来了任务就创建线程
 *
 * 以上三种线程池工作中用哪个？
 * 哪个都不用，一般自定义线程池，Excutors返回的线程池，FixedThreadPool和SingleThreadPool允许请求的队列长度为Integer.MAX_VALUE，可能堆积大量请求，导致OOM，CachedThreadPool和ScheduledThreadPool允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量线程，导致OOM
 *
 *
 *
 * 线程池七大参数：
 * 1、corePollSize 线程池中常驻核心线程数，创建了线程池后，当有任务过来，就会安排线程池中线程去执行，当线程池中的线程数达到corePoolSize后，就会把到达的任务放到缓存队列中
 * 2、maximumPoolSize 线程池能够容纳同时执行的最大线程数，值必须大于等于1
 * 3、keepAliveTime 空闲线程的最大存活时间，只有在线程池中的数量大于corePoolSize时，该参数才会起作用，当线程池数量超过corePoolSize时，当空闲时间超过keepAliveTime时，空闲线程会被销毁，直到线程池中线程数量不大于corePoolSize为止
 * 4、unit  keepAliveTime的单位
 * 5、workQueue 任务队列，被提交但未执行的任务
 * 6、threadFactory 生成线程池中工作线程的线程工厂，用于创建线程，一般用默认的
 * 7、handler 拒绝策略，当队列满了，并且哦给你做线程大于等于线程池的最大线程数时如何来拒绝请求执行的runnable的策略
 *
 * @author zc
 * @create 2019/7/4
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //查看计算机核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        //1、new ThreadPoolExecutor.AbortPolicy() （默认） 直接抛出异常
        //2、new ThreadPoolExecutor.CallerRunsPolicy()调用者运行，将任务回退到调用者执行
        //3、new ThreadPoolExecutor.DiscardOldestPolicy()  抛弃队列中等待最长的任务，然后把新任务加入队列中
        //4、new ThreadPoolExecutor.DiscardPolicy() 直接丢弃任务，不处理不抛异常

        try {
            for (int i = 0; i < 8; i++) {
                final int temp = i;
                executor.execute(() -> System.out.println(Thread.currentThread().getName()+"----------"+temp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }



    }


}

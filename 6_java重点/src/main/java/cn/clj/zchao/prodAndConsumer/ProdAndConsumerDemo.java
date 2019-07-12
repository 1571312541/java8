package cn.clj.zchao.prodAndConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈生产者消费者demo，在blockingQueue包下BlockingQueueDemo也是生产者消费者demo〉
 *
 * @author 22902
 * @create 2019/7/2
 */
public class ProdAndConsumerDemo {

    private volatile boolean flag = true;
    private AtomicInteger integer = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public ProdAndConsumerDemo(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void prod() throws InterruptedException {
        String data;
        boolean i;
        while (flag) {
            data = integer.incrementAndGet() + "";
            i = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (i) {
                System.out.println(Thread.currentThread().getName() + "数据插入" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "数据插入" + data + "失败");

            }
            //每1s生产一个
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void consumer() throws InterruptedException {
        String data;
        String i;
        while (flag) {
            i = blockingQueue.poll(2,TimeUnit.SECONDS);
            if (i==null||i.equalsIgnoreCase("")) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "停止！！！, 超过2s没有取到，退出");
                return;
            } else {
                System.out.println(Thread.currentThread().getName() + "数据取出" + i + "成功");

            }

        }
    }


    public void stop() throws InterruptedException {
        this.flag = false;
    }


    public static void main(String[] args) {

        ProdAndConsumerDemo prodAndConsumerDemo = new ProdAndConsumerDemo(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            try {
                prodAndConsumerDemo.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"消费线程启动");
            try {
                prodAndConsumerDemo.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2").start();

        try {
            //暂停5s
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            prodAndConsumerDemo.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("结束");
    }
}
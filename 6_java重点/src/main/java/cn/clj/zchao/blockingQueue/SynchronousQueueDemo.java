package cn.clj.zchao.blockingQueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 〈SynchronousQueue是一个不存储元素的阻塞队列〉
 *
 *  每一个put操作必须等待一个take操作才能继续put
 *
 * @author 22902
 * @create 2019/6/18
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"put a");
                synchronousQueue.put("a");
                System.out.println(Thread.currentThread().getName()+"put b");
                synchronousQueue.put("b");
                System.out.println(Thread.currentThread().getName()+"put c");
                synchronousQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread1").start();

        new Thread(() -> {
            try {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("take "+synchronousQueue.take());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("take "+synchronousQueue.take());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("take "+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2").start();


    }

}

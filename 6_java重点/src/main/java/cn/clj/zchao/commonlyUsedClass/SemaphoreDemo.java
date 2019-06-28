package cn.clj.zchao.commonlyUsedClass;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 〈信号量，两个目的，1、多个共享资源的互斥使用，2、并发线程数的控制〉
 *
 * @author zc
 * @create 2019/6/14
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
           new Thread(()->{
               try {
                   semaphore.acquire();
                   System.out.println(Thread.currentThread().getName() + " 进入 停车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                   System.out.println(Thread.currentThread().getName() + " 驶出   停车位");


               } catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                   semaphore.release();
               }
           },"thread"+i).start();

        }


    }


}

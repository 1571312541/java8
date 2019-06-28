package cn.clj.zchao.commonlyUsedClass;

import java.util.concurrent.CountDownLatch;

/**
 * 〈CountDownLatch〉
 *    可以让一些线程阻塞，直到一些线程完成一些操作后才被唤醒
 *   主要有两个方法，一个是使线程阻塞，await()方法，一个是使计数器减一，直到计数器到0 ，countDown()方法,其阻塞的线程会被唤醒，继续执行操作
 * @author zc
 * @create 2019/6/14
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        try {
            //总共5歌计数
            CountDownLatch countDownLatch = new CountDownLatch(5);
            for (int i = 0; i < 5; i++) {
                new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"一些初始化操作");
                    countDownLatch.countDown();
                },"thread"+i).start();
            }
            //等待count减小到0
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"其他操作");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

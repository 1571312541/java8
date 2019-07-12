package cn.clj.zchao.blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈生产者消费者模式〉
 *  1、一个初始值为0的变量，两个线程交替操作，一个线程加1 另一个线程减1
 *
 *  阻塞队列 （BlockingQueue接口，和List接口是Collection下的接口）是一个队列
 * 在多线程情况下，在某些情况下会挂起线程，即阻塞，一旦条件满足，挂起的线程会被唤醒
 * 使用BlockingQueue ，不需要关系什么时候需要阻塞线程，什么时候需要唤醒线程。
 * 使用的地方：生产者消费者模式、线程池、消息中间件
 * @author zc
 * @create 2019/6/20
 */
public class BlockingQueueDemo {

    private static Integer num = 0;

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void add() {
        lock.lock();
        try {

            //使用while判断，避免虚假唤醒的问题，判断一次之后再次判断，循环判断
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println("num = " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void subtraction() {
        lock.lock();
        try {
            while (num != 1) {
                condition.await();
            }
            num--;
            System.out.println("num = " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //两个生产，两个消费，如果前边不用while判断用if的话，会有问题，出现虚假唤醒
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                add();
            }
        },"thread1").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                subtraction();
            }
        },"thread2").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                add();
            }
        },"thread3").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                subtraction();
            }
        },"thread4").start();
    }



}

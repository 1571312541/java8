package cn.clj.zchao.blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈生产者消费者模式〉
 *  1、一个初始值为0的变量，两个线程交替操作，一个线程加1 另一个线程减1
 *
 *
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

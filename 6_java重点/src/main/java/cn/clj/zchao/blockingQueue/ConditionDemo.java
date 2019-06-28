package cn.clj.zchao.blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈指定唤醒一些线程〉
 * Thread1打印5次，Thread2打印10次，Thread3打印15次，共打印5轮
 *
 * @author zc
 * @create 2019/6/21
 */
public class ConditionDemo {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print() {
        new Thread(() -> {
            lock.lock();
            try {
                while (num != 1) {
                    condition1.await();
                }
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "  --  " + i);
                }
                num = 2;
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "Thread1").start();
        new Thread(() -> {
            lock.lock();
            try {
                while (num != 2) {
                    condition2.await();
                }
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "  --  " + i);
                }
                num = 3;
                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "Thread2").start();
        new Thread(() -> {
            lock.lock();
            try {
                while (num != 3) {
                    condition3.await();
                }
                for (int i = 1; i <= 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "  --  " + i);
                }
                num = 1;
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "Thread3").start();
    }

    public static void main(String[] args) {

        ConditionDemo conditionDemo = new ConditionDemo();
        for (int i = 0; i < 5; i++) {
            conditionDemo.print();

        }


    }


}

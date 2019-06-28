package cn.clj.zchao.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈可重入锁（递归锁）〉
 *  指同一个线程外层函数获取锁之后,内层递归函数仍能获取该锁
 *  同一个线程在外层获取锁后,进入内层会自动获取锁
 * 线程可以进入任意一个已经拥有锁的所同步的代码块
 * 即
 * 一个synchronized修饰的同步方法1中调用了另一个synchronized修饰的同步方法2，他们持有同一个锁。
 * 可重入锁 最大优点是避免死锁
 * ReentrantLock、synchronized就是可重入锁，
 *
 * @author 22902
 * @create 2019/6/12
 */
public class ReenterLockDemo {

    public static void main(String[] args) {
/*        Phone phone = new Phone();
        new Thread(()->{
            phone.sendMsg();
        },"thread1").start();

        new Thread(()->{
            phone.sendMsg();
        },"thread2").start();*/

        Mobile mobile = new Mobile();
        new Thread(()->{
            mobile.sendMsg();
        },"thread3").start();
        new Thread(() -> {
            mobile.sendMsg();
        }, "thread4").start();


    }

}

class Phone{

    public synchronized void sendMsg() {
        System.out.println(Thread.currentThread().getName() + "   sendMsg");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "   sendEmail");
    }

}

class Mobile{

    public void sendMsg() {
        Lock lock = new ReentrantLock();
        lock.lock(); //即使多个锁也没事  lock()和unlock()要对应,加锁几次解锁就几次
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "   sendMsg");
            sendEmail();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void sendEmail() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "   sendEmail");
        }finally {
            lock.unlock();
        }
    }


}

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
 *
 *
 * synchronize 和Lock区别
 * 1、原始构成
 * (1)synchronized是关键字，属于JVM层面  monitorenter、monitorexit 底层通过monito等方法也是r对象完成，其wait、notify也是依赖montinor对象 只有在同步代码块或者方法中才能调用
 * (2)lock是具体类 是api层面
 * 2、使用方法
 * (1)synchronized不需要手动释放锁，代码执行完后自动释放
 * (2)reentrantLock需要手动释放，如果没有释放可能导致死锁，需要lock（）unlock（）配合 try finally完成
 * 3、是否可以中断
 * (1)synchornized不可中断，除非异常或者正常执行完毕
 * (2)ReentrantLock可以中断，设置超时事件 trylock（Long timeout，TimeUnit unit）  LockInteruptibly（）放代码块中 调用interrupt（）方法中断
 * 4、加锁是否公平
 * (1)synchornized是非公平锁
 * (2)ReentrantLock默认非公平锁，构造方法传布尔，true是公平锁，false是非公平锁
 * 5、锁绑定条件Condition
 * synchronized没有，只能随机唤醒一个，或者唤醒所有的
 * ReentrantLock 用来实现分组唤醒需要唤醒的线程，精确唤醒
 *
 * @author zc
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

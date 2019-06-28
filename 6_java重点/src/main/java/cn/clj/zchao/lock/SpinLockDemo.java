package cn.clj.zchao.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 〈自旋锁 示例〉
 *
 *  自旋锁 指线程尝试获取锁时不会阻塞，会循环去尝试获取锁，减少了上下文切换的消耗，但是消耗cpu
 *
 * @author 22902
 * @create 2019/6/12
 */
public class SpinLockDemo {

    /**
     * 原子引用线程
     */
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    /**
     * 自定义lock方法
     */
    public void myLock() {
        Thread thread = Thread.currentThread();
        while (!threadAtomicReference.compareAndSet(null, thread)) {
            //当线程1持有该锁后,线程2无法获取该锁,
            // 只有等待线程1释放锁后,线程2才能获取锁
        }
        System.out.println(thread.getName() + "thread lock");
    }

    /**
     * 自定义unLock方法
     */
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "thread unlick");

        ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
        rwlock.writeLock().lock();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwlock.writeLock().unlock();
        }

    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            //模拟持有锁5s
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"thread1").start();

        new Thread(()->{
            //让thread1先执行,所有thread2停1s
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"thread2").start();

    }




}

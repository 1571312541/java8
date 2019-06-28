package cn.clj.zchao.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 〈独占锁  该锁只能被一个线程持有  ReentrantLock和synchorized是独占锁
 *    共享锁  该锁可以被多个线程持有  ReentrantReadWriteLock的读是共享锁 写是独占锁
 *
 *    读写、写写、写读的过程是互斥的
 *    例1 说明了 在写的过程中被打断   而 写操作是原子+独占，过程必须完整，中间不允许打断
 *
 * 〉
 *
 * @author  zc
 * @date 2019/6/13
 */
public class ReadWriteLockDemo {

    private volatile Map<String, Object> map = new HashMap<>();
    ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void set(String key,Object value) {
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "存 值 开始" + value);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "存  值结束" + value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwlock.writeLock().unlock();
        }
    }
    
    public void get(String key) {
       rwlock.readLock().lock();
       try {
           System.out.println(Thread.currentThread().getName() + "取 值 开始");
           TimeUnit.MILLISECONDS.sleep(300);
           Object value = map.get(key);
           System.out.println(Thread.currentThread().getName() + "取 值结束" + value);
       } catch (Exception e) {
           e.printStackTrace();
       }finally {
           rwlock.readLock().unlock();
       }
    }
    //例 2
    public static void main(String[] args) {

        ReadWriteLockDemo demo = new ReadWriteLockDemo();

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                demo.set(Thread.currentThread().getName(),temp);
            },"thread"+i).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                demo.get(Thread.currentThread().getName());
            },"thread"+i).start();
        }


    }
    //例 1  如果不加锁 ，没有    ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
     /*
    public static void main(String[] args) {
        该注释中代码 其结果如下
        thread1存 值 开始1
        thread3存 值 开始3
        thread2存 值 开始2
        thread0存 值 开始0
        thread4存 值 开始4
        thread0取 值 开始
        thread0取 值结束null
        thread1取 值 开始
        thread2取 值 开始
        thread2取 值结束null
        thread1取 值结束null
        thread4取 值 开始
        thread3取 值 开始
        thread4取 值结束null
        thread3取 值结束null
        thread4存  值结束4
        thread2存  值结束2
        thread3存  值结束3
        thread0存  值结束0
        thread1存  值结束1
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                    demo.set(Thread.currentThread().getName(),temp);
            },"thread"+i).start();
        }
       for (int i = 0; i < 5; i++) {
            new Thread(()->{
                demo.get(Thread.currentThread().getName());
            },"thread"+i).start();
        }
    }
        */
}

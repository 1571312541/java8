package cn.clj.zchao.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 〈死锁示例〉
 *  两个线程或多个线程在执行过程中，因争抢资源造成互相等待的现象
 *   产生死锁原因：
 *      1、系统资源不足
 *      2、进行推进顺序不合适
 *      3、资源分配不当
 *
 * @author 22902
 * @create 2019/7/11
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        MyResources r = new MyResources("lockAAAA", "lockBBBB");
        MyResources r2 = new MyResources("lockBBBB", "lockAAAA");
        FutureTask<Integer> futureTask = new FutureTask<>(r);
        FutureTask<Integer> futureTask2 = new FutureTask<>(r2);
        new Thread(futureTask,"threadAAAAA").start();
        new Thread(futureTask2,"threadBBBBB").start();
    }

    static class MyResources implements Callable<Integer> {
        private final String lockA;
        private final String lockB;

        public MyResources(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Integer call() throws Exception {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "持有锁" + lockA + "尝试获取锁" + lockB);
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "持有锁" + lockB + "尝试获取锁" + lockA);
                }
            }
            return null;
        }

    }


}

package cn.clj.zchao.ABADemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 〈验证、解决ABA问题〉
 *
 * @author 22902
 * @create 2019/6/12
 */
public class ABADemo {

    public static void main(String[] args) {

        // ======= ABA问题的产生
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        new Thread(()->{
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        },"thread1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "  " + atomicReference.get());
        },"thread2").start();

        //=========ABA问题的解决
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp = stampedReference.getStamp();
            stampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            stampedReference.compareAndSet(101, 100, stamp, stamp + 1);
        },"thread3").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp = stampedReference.getStamp();
            System.out.println(
                    stampedReference.compareAndSet(100, 2019, stamp, stamp + 1) + "  " +
                    stampedReference.getStamp()+"  " +
                    stampedReference.getReference());
        },"thread4").start();

    }

}

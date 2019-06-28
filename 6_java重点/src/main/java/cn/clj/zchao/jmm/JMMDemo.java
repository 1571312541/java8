package cn.clj.zchao.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *                     .::::.
 *                   .::::::::.
 *                  :::::::::::    佛主保佑、永无Bug
 *              ..:::::::::::'
 *            '::::::::::::'
 *              .::::::::::
 *         '::::::::::::::..
 *              ..::::::::::::.
 *            ``::::::::::::::::
 *             ::::``:::::::::'        .:::.
 *            ::::'   ':::::'       .::::::::.
 *          .::::'      ::::     .:::::::'::::.
 *         .:::'       :::::  .:::::::::' ':::::.
 *        .::'        :::::.:::::::::'      ':::::.
 *       .::'         ::::::::::::::'         ``::::.
 *   ...:::           ::::::::::::'              ``::.
 *  ```` ':.          ':::::::::'                  ::::..
 *                     '.:::::'                    ':'````..
 *
 *  volatile
 *  java虚拟机提供的轻量级的同步机制
 *  特性：保证可见性，不保证原子性，禁止指令重排
 *  ------------------------
 *  JMM（Java Memory Model）java内存模型，是一种抽象的概念，并不真实存在，描述的是一组规则或规范，通过该规范定义了程序中各个变量的访问方式
 *  JMM 特性 可见性、原子性、有序性
 *  1、可见性 一个线程对数据的操作对其他线程是可见的，可以读取到改变后的值
 *  2、原子性  不可分割，完整性，对数据的操作要么全成功，要么全失败
 *  3、有序性
 *
 *
 */

class Number{

//    int num = 0;
    volatile int num = 0;

    public void changeNum() {
        num = 1;
    }


    public void addNum() {
        num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }



}

public class JMMDemo {
    public static void main( String[] args ) {
//        test1();
//        test2();
        test3();

    }


    /**
     * 该demo验证可见性,
     *  当变量num没有被volatile修饰时，它的修改对其他线程不是可见的，main线程的num值不会变，会一直循环，不会打印main完成
     *  当变量num被volatile修饰时，它的修改对其他线程就是可见的，main线程的num值会变成1，会打印main完成
     */
    public static void test1() {
        Number number = new Number();
        //创建一个线程
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "  ---- in");
            try {
                //暂停3s
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number.changeNum();
            System.out.println(Thread.currentThread().getName() + "更新值为：" + number.num);
        },"my thread").start();

        //第二个线程是main线程

        while (number.num == 0) {
            //main程序会在这里循环，等待num变化
        }

        System.out.println(Thread.currentThread().getName() + "完成");

    }

    /**
     * 该demo验证非原子性,
     * 不管变量num有没有被volatile修饰，开启20个线程，每个线程调用addNum方法100次,得到的结果都不是20000
     * 如果是原子性，结果应该是20000
     *
     */
    public static void test2() {
        Number number = new Number();
        //20个线程，每个线程调用addNum方法100次
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    number.addNum();
                }
            },"my thread"+i).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(number.num);
    }

    /**
     * 该demo验证原子性,(自选锁)
     */
     public static void test3() {
        Number number = new Number();
        //20个线程，每个线程调用addNum方法100次
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    number.addAtomic();
                }
            },"my thread"+i).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(number.atomicInteger);
    }



}

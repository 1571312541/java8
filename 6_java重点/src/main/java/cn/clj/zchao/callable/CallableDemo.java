package cn.clj.zchao.callable;

import java.util.concurrent.*;

/**
 * 〈开启线程的方式
 * 1、继承Thread类
 * 2、实现Runnable接口
 * 3、实现Callable接口〉
 *
 * @author zc
 * @create 2019/7/3
 */
public class CallableDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        myTest3();

    }

    public static void myTest1() throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask,"AA").start();
//        Integer integer = futureTask.get(); //尽量放到最后
        Integer s = 100;
        System.out.println(Thread.currentThread().getName()+"------");
        //futureTask.get()要求获取callable线程的结果，如果没有计算完成，就会阻塞
        while (!futureTask.isDone()) {

        }
        Integer integer = futureTask.get();
        System.out.println(integer+s);
    }

    public static void myTest2() throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        //多个线程抢一个futureTask，只执行一个，如果想执行多个，用不同的futureTask,见myTest3
        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();
        Integer s = 100;
        System.out.println(Thread.currentThread().getName()+"------");
        while (!futureTask.isDone()) {

        }
        Integer integer = futureTask.get();
        System.out.println(integer+s);
    }


    public static void myTest3() throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());
        //多个线程抢一个futureTask，只执行一个，如果想执行多个，用不同的futureTask
        new Thread(futureTask,"AA").start();
        new Thread(futureTask2,"BB").start();
        Integer s = 100;
        System.out.println(Thread.currentThread().getName()+"------");
        while (!futureTask.isDone()) {

        }
        Integer integer = futureTask.get();
        System.out.println(integer+s);
    }



}

class MyThread implements Callable<Integer> {
    public MyThread(){
        System.out.println(Thread.currentThread().getName() + "进入callable");
    }


    @Override
    public Integer call() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 1;
    }
}


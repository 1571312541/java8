package cn.clj.zchao.blockingQueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 〈ArrayBlockingQueue 由数组组成的有界阻塞队列〉
 *  先进先出
 *      add             offer       put         offer(obj , timeout , timeUtil)
 *      remove          poll        take        poll(timeout , timeUtil)
 *      element         peek
 *   可能会报异常     不报异常     会阻塞       等待指定时间
 * @author zc
 * @create 2019/6/18
 */
public class ArrayBlockingQueueDemo {

    @Test
    public void test1() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // 定义的阻塞队列长度为3 ，当使用add方法添加时，超过这个数，报异常  java.lang.IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("d"));

        //当阻塞队列中没有元素时，报异常，java.util.NoSuchElementException
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //定义的阻塞队列长度为3 ，当使用remove方法移除时，超过这个数，报异常  java.util.NoSuchElementException
        //System.out.println(blockingQueue.remove());

    }

    @Test
    public void test2() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //定义的阻塞队列长度为3 ，当继续向队列中offer添加元素时，不报异常，返回插入失败
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //定义的阻塞队列长度为3 ，当队列中poll移除元素超过3时，不报异常，返回null
        System.out.println(blockingQueue.poll());
        //当阻塞队列中没有元素时，返回null
        System.out.println(blockingQueue.peek());

    }

    @Test
    public void test3() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //定义的阻塞队列长度为3，当队列中put的数超过3时，后边的会一直阻塞
        //blockingQueue.put("d");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        //阻塞队列长度为3，当队列中take的数超过3时，会一直阻塞
        //blockingQueue.take();

    }


    @Test
    public void test4() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));
        //定义的阻塞队列长度为3，当队列中offer的数超过3时，等待达到指定的等待时间后还不能插入就返回false
        System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        //定义的阻塞队列长度为3，当队列中poll的数超过3个时，等待达到指定的等待时间后还不能获取到就返回null
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));

        System.out.println(blockingQueue.peek());

    }


}

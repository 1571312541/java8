package cn.clj.zchao.commonlyUsedClass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 〈与CountDownLatch相反〉
 *
 *  可循环的屏障，一组线程到达屏障时阻塞，直到所有线程达到这个屏障，屏障才会消失，继续操作
 *
 * @author zc
 * @create 2019/6/14
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("出发去旅游！");
        });

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(temp+" 赶到地点");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "thread" + i).start();
        }

    }


}



package cn.clj.zchao.oom;

import java.util.concurrent.TimeUnit;

/**
 * 〈堆溢出示例〉
 *
 *  高并发请求服务器时，经常出现java.lang.OutOfMemoryError:unable to create new native thread，与其对应的平台有关
 *  导致原因：
 *         1、一个进程创建太多线程，超过系统承受极限
 *         2、服务器不允许创建这么多线程，linux系统默认允许单个进程创建的线程数时1024
 *  解决方法：
 *         1、想办法降低应用程序创建线程的数量，分析是否真的需要创建这么多线程
 *         2、如果有的应用确实需要这么多线程，linux可以修改服务器配置，扩大限制
 *
 *  此程序在linux环境非root下运行
 *
 * @author zc
 * @create 2019/7/19
 */
public class OutOfMemoryError_thread {

    public static void main(String[] args) {
        for (int i = 1; ; i++) {
            System.out.println(i+" 线程");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }, i + "").start();

        }
    }




}

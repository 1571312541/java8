package cn.clj.zchao.gc;

import java.lang.ref.SoftReference;

/**
 * 〈软引用示例〉
 *
 *  软引用是是一种相对强引用弱化了的引用，需要java.lang.ref.SoftReference实现，
 *  可以让对象豁免一些垃圾手机。
 *  对于只有软引用的对象来说，当系统内存充足时，该引用不会被回收；当系统内存不足时，该引用会被回收
 *  软引用通常用在对内存敏感的程序中，在高速缓存中就有用到软引用，内存足够的时候就保留，不够就回收
 *
 *
 * @author zc
 * @create 2019/7/12
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
//        enoughMemoryTest();
        NotEnoughMemoryTest();
    }

    /**
     * 内存充足
     */
    public static void enoughMemoryTest() {
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);
        o = null;
        System.out.println(o);//null
        System.out.println(softReference.get());// not null
        //手动gc
        System.gc();
        System.out.println(o);//null
        System.out.println(softReference.get());// not null
    }

    /**
     * 内存不足
     * JVM配置，故意产生大对象并配置小内存，让其内存不够用产生OOM，看软引用回收情况
     *  -Xms5m -Xmx5m -XX:+PrintCommandLineFlags
     */
    public static void NotEnoughMemoryTest() {
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);
        o = null;
        System.out.println(o);//null
        System.out.println(softReference.get());// not null
        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(o);  //null
            System.out.println(softReference.get());//null
        }

    }

}

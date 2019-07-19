package cn.clj.zchao.gc;

import java.lang.ref.WeakReference;

/**
 * 〈弱引用示例〉
 *  对于只有弱引用的对象来说，只要垃圾回收机制一运行，不管JVM的内存空间是否够用，都会回收
 *
 * @author zc
 * @create 2019/7/15
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);
        //当前内存是足够的，把o置为null
        o = null;
        System.gc();
        //gc后弱引用会被回收
        System.out.println(weakReference.get());//null
    }

}

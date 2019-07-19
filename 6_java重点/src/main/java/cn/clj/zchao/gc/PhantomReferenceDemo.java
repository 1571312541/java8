package cn.clj.zchao.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 〈虚引用示例〉
 *  java提供四种引用类型
 *  ReferenceQueue用来配合引用
 *  创建引用的时候可以指定关联的引用队列，将gc将对象回收的时候，会把引用放入队列中，
 *  如果某个虚引用被加入到引用队列中，那么在该对象被回收前做一些必要的行动，相当一一种通知机制
 *  当关联的引用队列中有数据时，意味着引用指向的堆内存中的对象被回收
 *
 * @author zc
 * @create 2019/7/17
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o, referenceQueue);
        System.out.println(o);//有值
        System.out.println(phantomReference.get());//null
        System.out.println(referenceQueue.poll());//null
        o = null;
        System.gc();
        System.out.println(o);//null
        System.out.println(phantomReference.get());//null
        System.out.println(referenceQueue.poll());//有值
    }

}

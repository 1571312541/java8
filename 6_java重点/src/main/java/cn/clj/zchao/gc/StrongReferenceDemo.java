package cn.clj.zchao.gc;

/**
 * 〈强引用示例〉
 *
 *  当内存不足时，JVM开始垃圾回收，对于强引用的对象，就算出现OOM也不会对该对象进行回收
 *  java中最常见的就是强引用，把一个对象赋给一个引用变量，这个引用变量就是强引用，即使该对象永不使用，都不会被回收
 *  因此，强引用是内存泄漏的主要原因
 *  对于一个普通对象，如果没有其他引用关系，只要超过了引用的作用域或者显示的将其赋为null，一般就可以被回收，具体还要看垃圾收集策略
 *
 * @author zc
 * @create 2019/7/12
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object o = new Object();
        Object o2 = o;
        o = null;
        System.gc();
        System.out.println(o);
        System.out.println(o2);
    }

}

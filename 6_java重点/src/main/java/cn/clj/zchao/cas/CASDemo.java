package cn.clj.zchao.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈cas  比较并交换  compareAndSet〉
 *
 * @author 22902
 * @create 2019/6/11
 */
public class CASDemo {

    public static void main(String[] args) {
        //给integer一个初始值 5
        AtomicInteger integer = new AtomicInteger(5);
        //如果期望值是5,则修改为10
        System.out.println(integer.compareAndSet(5, 10) + "now num = " + integer);
        //如果期望值是5,则修改为20
        System.out.println(integer.compareAndSet(5, 20) + "now num = " + integer);

    }

}

package cn.clj.zchao.oom;

import java.util.Random;

/**
 * 〈堆溢出错误示例〉 -- OutOfMemoryError: Java heap space
 *
 *      创建大对象，超过堆内存就会出现如下错误
 *
 * @author zc
 * @create 2019/7/18
 */
public class OutOfMemoryErrorDemo_heap {
    /**
     * 配置VM option 把堆内存改小点 ： -Xmx5m -Xms5m
     *  （例如如 Byte[] b = new Byte[50 * 1024 * 1024]）
     */
    public static void main(String[] args) {
        String s = "werwe";
        while (true) {
            s += s + new Random().nextInt(2022222213);
            String intern = s.intern();
            System.out.println(intern);
        }
    }

}

package cn.clj.zchao.oom;

import java.util.ArrayList;

/**
 * 〈堆溢出错误示例〉  --  GC overhead limit exceeded
 *
 *  gc回收时间过长会出现该异常，过长的定义是，超过98%的时间用来做gc，并且回收了不到2%的堆内存
 *  连续多次gc都回收了不到2%的堆内存就会出现该异常
 *  如果不抛异常，会导致gc清理的内存很快填满，迫使gc再次执行，恶性循环，CPU使用率一直是100%，而gc却没有任何成效
 *
 * @author zc
 * @create 2019/7/18
 */
public class OutOfMemoryErrorDemo_gc {
    /**
     * java.lang.OutOfMemoryError: GC overhead limit exceeded
     *  VM-option : -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails
     */
    public static void main(String[] args) {
        try {
            int i = 0;
            ArrayList<String> list = new ArrayList<>();
            while (true) {
                list.add(String.valueOf(i++).intern());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

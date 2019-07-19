package cn.clj.zchao.oom;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * 〈堆溢出错误示例〉
 *      java.lang.OutOfMemoryError: Direct buffer memory
 *     写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）和缓冲区（Buffer）的I/O方式，
 *     它可以使用Native函数直接分配堆外内存，然后通过一个存储在java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作，
 *     能在一些场景中提高性能，因为它避免了在java堆和Native堆中来回复制数据
 *     ByteBuffer.allocate(capability) 第一种方式是分配java堆内存，属于GC管理范围，由于需要拷贝，所以速度相对较慢
 *     ByteBuffer.allocateDirect(capability) 第二种方式分配OS本地内存，不属于GC管理范围，速度相对较快
 *
 *     但是，如果不断分配本地内存，堆内存却很少使用，那么JVM不需要执行GC，DirectByteBuffer对象得不到回收，
 *     这时候，堆内存充足，但本地内存用光了，再次尝试分配本地内存时，会出现异常 java.lang.OutOfMemoryError: Direct buffer memory
 *
 *  VM-option：-Xms5m -Xmx5m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m -XX:+PrintCommandLineFlags
 *  把最大对外内存设置为5m，当对外内存满了，并且java堆内存没有满，不会触发gc回收，就会出现该异常
 * @author zc
 * @create 2019/7/18
 */
public class OutOfMemoryErrorDemo_DirectBuffer {
    public static void main(String[] args) {
        System.out.println("当前maxDirectMemory：" + VM.maxDirectMemory() / 1024 / 1024 + "MB");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);


    }

}

package cn.clj.zchao.gc;

/**
 * 〈boolean类型，是否打印GC收集细节示例〉
 * 〈KV设值类型，元空间大小示例〉
 *
 *VM参数类型：
 * 1、标配参数
 *   (1)java -version
 *   (2)java -help
 *   (3)java -showversion
 * 2、x参数
 *   (1)java -Xint -version       -Xint 解释执行
 *   (2)java -Xcomp -version	  -Xcomp 先编译在执行，混合模式
 *   (3)java -Xmixed -version	  -Xmixed 第一次使用就编译成本地代码
 * 3、xx参数
 *   (1)boolean类型
 *      ①公式
 *        1)-XX：+或者-某个属性值
 *        2)+代表开启  -代表关闭
 *      ②示例
 *        1)是否打印GC收集细节
 *        2)是否使用串行垃圾回收器
 *   (2)KV设值类型
 *      ①公式
 *          1)-XX：属性key=属性值value
 *      ②示例
 *          1)-XX:MetaspaceSize=128m
 *          2)-XX:MaxTenuringThreshold=15 //到达养老区的限制
 *
 *
 *  如何查看JVM初始化参数：
 *      1、查看初始默认
 *          (1)java -XX: +PrintFlagsInitial
 *      2、查看修改更新后的
 *          java -XX: +PrintFlagsFinal -version
 *
 *    JVM常用参数
 *      1、-xms  初始内存大小 -XX：InitialHeapSize，默认为物理内存1/64
 *      2、-xmx  最大内存大小  -XX：MaxHeapSize，默认物理内存1/4
 *      3、-xss  设置单个线程栈的大小，-XX：ThreadStackSize 一般512k-1024k
 *      4、-xmn  设置年轻代大小
 *      5、-XX：MetaspaceSize 设置元空间大小
 *      6、-XX:SurvivorRatio  设置新生代eden和S0、S1的比例
 *          默认-XX:SurvivorRatio=8  ，Eden:S0:S1=8:1:1
 *      7、-XX:newRatio  设置新生代、老年代在对结构的占比
 *          默认-XX:newRatio=2  新生代占1，老年代占2，年轻代占整个堆的1/3
 *      8、-XX:MaxTenuringThreshold  设置垃圾最大年龄
 *
 * @author zc
 * @create 2019/7/11
 */
public class GCDemo {
    /**
     * 〈boolean类型，是否打印GC收集细节示例〉
     *
     *  在 Run--edit configuration -- 设置VM：options -XX:+PrintGCDetails
     *  Terminal控制台输入
     *   D:\code\java8> jps -l
     *       10128 org.jetbrains.jps.cmdline.Launcher
     *       17712
     *       19568 org.jetbrains.jps.cmdline.Launcher
     *       15764 cn.clj.zchao.gc.GCDemo
     *       16276 sun.tools.jps.Jps
     *
     *   D:\code\java8> jinfo -flag PrintGCDetails 15764
     *      -XX:+PrintGCDetails
     *
     *
     *
     *  〈KV设值类型，元空间大小示例〉
     *  Terminal控制台输入
     *  D:\code\java8>jps -l
     *      10128 org.jetbrains.jps.cmdline.Launcher
     *      17712
     *      7668 sun.tools.jps.Jps
     *      21608 org.jetbrains.jps.cmdline.Launcher
     *      21180 cn.clj.zchao.gc.GCDemo
     *
     *  D:\code\java8>jinfo -flag MetaspaceSize 21180
     *      -XX:MetaspaceSize=21807104 //默认21m多
     *
     *  在 Run--edit configuration -- 设置VM：options -XX:MetaspaceSize=128m
     *  D:\code\java8>jinfo -flag MetaspaceSize 21896
     *      -XX:MetaspaceSize=134217728//结果就是修改后的值 128m
     *
     *  //查看垃圾回收器  java -XX:+PrintCommandLineFlags -version
     *
     *  D:\code\platform-ejiajx-safe\platform-ejiajx-safe-parent> java -XX:+PrintCommandLineFlags -version
     *      -XX:InitialHeapSize=132044608 -XX:MaxHeapSize=2112713728 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividual
     *      Allocation -XX:+UseParallelGC  //UseParallelGC 就是垃圾回收器
     *      java version "1.8.0_181"
     *      Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
     *      Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
     *
     *
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("*************  i am in GCDemo  *****************");
        Thread.sleep(Integer.MAX_VALUE);
    }

}

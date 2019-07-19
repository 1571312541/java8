volatile
JVM  --------类的加载
JMM
TCP
HashMap
ArrayList、HashSet
CAS
ABA问题

原子引用
公平、非公平锁
可重入锁（递归锁）
自旋锁
独占锁（写锁）、共享锁（写锁）、互斥锁
阻塞队列
synchronized和Lock区别
CountDownLatch、CyclicBarrier、Semaphone
事务的四个特性
线程池
ThreadLocal
redis的Pipeline 
死锁
GC
强引用、软引用、弱引用、虚引用
OOM





一、TCP
1、三次握手是为了建立双向连接

2、syn洪水攻击，导致server大量连接连不上client
3、 断开连接需要4次挥手，原因i、保证客户端发送的最后一个ACK报文段能够到达服务器ii、以防止已失效的报文段，骚扰服务器

4、再断连时，可能出现大量socket超时，再linux中可以开启TCP参数，可以加快timewait回收，大量closewait是被动关闭一端没有正确关闭，可能有bug

二、设计模式
1、单例
i、静态初始化（饿汉式）
类初始化时完成单例示例的创建，所以不会有并发问题，不管是否使用，都会创建
ii、双重排查 （懒汉式）
再真正用的时候才会创建，所以会有多线程并发，使用synchronized
iii、单例注册表
spring中bean就是通过这种模式实现

工厂模式
代理模式
适配器模式 slf4j如何只吃log4j
观察者模式：GRPC中支持流式请求
构造者模式















三、基础
Map
i、Java7 HashMap

1、是由数组和链表实现的（Java8是由数组+链表+红黑树 组成），数组中每一项是链表，通过计算hashcode决定对象存入的数组位置，用链表解决散列冲突（哈希冲突），新加入的放在链头，最先加入的放在链尾，其链表的节点存的是键值对，从hashmap中get元素时，首先计算key的hashcode，找到数组中对应位置的某一元素，然后通过key的equals方法在对应位置的链表中找到需要的元素
2、容量是2的幂次方，因为更能减少key之间的碰撞，而加快查询的效率。
3、hashmap的resize
4、key的hashcode与equals方法改写 

ii、Java7 ConCurrentHashMap


ConcurrentHashMap 是一个 Segment 数组，Segment 通过继承 ReentrantLock 来进行加锁，所以每次需要加锁的操作锁住的是一个 segment，这样只要保证每个 Segment 是线程安全的，也就实现了全局的线程安全。大小默认是 16









iii、Java8 HashMap （加入了红黑数）

当链表中的元素超过了 8 个以后，会将链表转换为红黑树，查找的时候可以降低时间复杂度

iiii、Java8 ConCurrentHashMap



结构上和 Java8 的 HashMap 基本上一样





jvm内存模型



java8以后的jvm，对于堆内存，有伊甸园区，幸存者0区，幸存者1区，养老区，7之前叫永久代，8叫yang’l元空间



java内存模型，定义变量的访问规则



类的加载，子类委托给父类，父类还有父类，一直向上委托，如果父类可以加载就加载，不能就向下委托


JVM参数类型：
1、标配参数
(1)java -version
(2)java -help
(3)java -showversion
2、x参数
(1)java -Xint -version       -Xint 解释执行
(2)java -Xcomp -version	  -Xcomp 先编译在执行，混合模式
(3)java -Xmixed -version	  -Xmixed 第一次使用就编译成本地代码
3、xx参数
(1)boolean类型
①公式
1)-XX：+或者-某个属性值
2)+代表开启  -代表关闭
②示例
1)是否打印GC收集细节
2)是否使用串行垃圾回收器

(2)KV设值类型
①公式
1)-XX：属性key=属性值value
②示例
1)-XX:MetaspaceSize=128m
2)-XX:MaxTenuringThreshold=15

如何查看当前运行程序的配置：
jinfo -flag 配置项 进程编号
jinfo -flags 进程编号
进程编号由jps -l查询

-Xms和-Xmx属于什么类型
xx参数，-Xms等价于 -XX：InitialHeapSize  -Xmx等价于 -XX：MaxHeapSize

如何查看JVM初始化参数：
1、查看初始默认
(1)java -XX: +PrintFlagsInitial
2、查看修改更新后的
(1)java -XX: +PrintFlagsFinal -version


其中 = 和 ：=的区别是 ：=是修改过的，=是初始值

在运行java同时打印参数
java -XX：+PrintFlagsFinal -XX：MetaspaceSize=512m Hello（类的名字）

查看jvm垃圾回收器
java -XX：+PrintCommandLineFlags -version

JVM常用参数
1、-xms  初始内存大小 -XX：InitialHeapSize，默认为物理内存1/64
2、-xmx  最大内存大小  -XX：MaxHeapSize，默认物理内存1/4
3、-xss  设置单个线程栈的大小，-XX：ThreadStackSize 一般512k-1024k
4、-xmn  设置年轻代大小
5、-XX：MetaspaceSize 设置元空间大小
6、-XX:SurvivorRatio  设置新生代eden和S0、S1的比例
默认-XX:SurvivorRatio=8  ，Eden:S0:S1=8:1:1
7、-XX:newRatio  设置新生代、老年代在对结构的占比
默认-XX:newRatio=2  新生代占1，老年代占2，年轻代占整个堆的1/3
8、-XX:MaxTenuringThreshold  设置垃圾最大年龄

GC


线程共享的部分需要垃圾回收


常见垃圾回收算法
1、引用计数
2、复制
3、标记清除
4、标记整理

java堆从GC的角度还可以细分为新生代（Eden区，From Survivor区和TO Survivor区）和老年代

复制：

优点是因为整体复制没有浪费，缺点浪费空间

优点标记没有大面积复制，缺点导致内存碎片

优点没有内存碎片，移动对象需要成本

什么是GC Root
GCRoot就是一组必须活跃的引用
垃圾就是内存中已经不再使用到的空间

如何判断一个对象是否应该被回收
1、引用计数法

2、枚举根节点做可达性分析（跟搜索路径）


java可以做为GC Root的对象有哪些
1、虚拟机栈（栈帧中局部变量区）中引用的对象
2、方法区中的类静态属性引用的对象
3、方法区中常量引用的对象
4、本地方法栈中引用的对象

GC日志信息






元空间
元空间本质和永久代类似，都是对jvm规范中方法区的实现，元空间和永久代区别在于：元空间不在虚拟机中，而是在本地内存中，因此，默认情况下，元空间大小只收到本地内存限制

强引用、软引用、弱引用、虚引用
1、

2、软引用，在mybatis缓存用到了，在读取大量图片时应用

3、弱引用


4、虚引用



OOM

什么时异常Exception、错误Error：
1.exception和error都是继承了throwable类，在java中只有throwable类型的	实例才可以被抛出(throw)或捕获(catch)，它是异常处理机制的基本组成类型
2.exception和error体现了对不同异常情况的分类。exception是程序正常运行中，	可以预料的意外情况，并且应该被捕获，进行相应的处理
3.error是指在正常情况下，不大可能出现的情况，大部分的error都会导致程序	处于非正常的、不可恢复的状态。既然是非正常情况，所以不便于也不需要捕	获，常见的比如OutOfMemoryError之类，都是Error的子类
4.Exception又分为可检查异常和不可检查异常，可检查异常必须处理，这是编译	期检查的一部分。不可检查异常就是运行时异常，类似NullPointerException，	通常是可以编码避免的逻辑错误，具体可以根据需要来判断是否需要捕获，并不	会在编译期强制要求


1、java.lang.StackOverflowError
2、java.lang.OutOfMemoryError:java heap space
3、java.lang.OutOfMemoryError:GC overhead limit exceeded
4、java.lang.OutOfMemoryError:Direct buffer memory
5、java.lang.OutOfMemoryError:unable to create new native thread
6、java.lang.OutOfMemoryError:Metaspace

1、局部数组变量空间太大,或者函数出现无限递归调用，就会出现栈溢出
2、创建大对象，超过堆内存就会出现
3、gc回收时间过长会出现该异常，过长的定义是，超过98%的时间用来做gc，并且回收不到	2%的堆内存，连续多次gc都回收了不到2%的堆内存就会出现该异常，如果不抛异常，会	导致gc清理的内存很快填满，迫使gc再次执行，恶性循环，CPU使用率一直是100%，	而gc却没有任何成效
4、写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）和缓	冲区（Buffer）的I/O方式，它可以使用Native函数直接分配堆外内存，然后通过一个存储	在java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作，能在一些场景中提高	性能，因为它避免了在java堆和Native堆中来回复制数据
ByteBuffer.allocate(capability) 第一种方式是分配java堆内存，属于GC管理范围，由于需要拷	贝，所以速度相对较慢
ByteBuffer.allocateDirect(capability) 第二种方式分配OS本地内存，不属于GC管理范围，速度	相对较快
但是，如果不断分配本地内存，堆内存却很少使用，那么JVM不需要执行GC，		DirectByteBuffer对象得不到回收，这时候，堆内存充足，但本地内存用光了，再次尝试分	配本地内存时，会出现异常 java.lang.OutOfMemoryError: Direct buffer memory


volatile
 java虚拟机提供的轻量级的同步机制
特性：保证可见性，不保证原子性，禁止指令重排

JMM（Java Memory Model）java内存模型，是一种抽象的概念，并不真实存在，描述的是一组规则或规范，通过该规范定义了程序中各个变量的访问方式，使线程安全获得保证
JMM 特性 可见性、原子性、有序性

可见性：当一个线程在自己工作内存中修改变量后，写回主内存后会通知其他修改这个变量的线程，其他线程中的变量作废，重新从主内存中拿，这就是可见性（修改后的变量立即对其他线程可见）

原子性：  不可分割，完整性，对数据的操作要么全成功，要么全失败
不保证原子性，当一个线程往主内存写回数据时，挂起了，另一个线程写回了，而开始的线程又马上写回去，这时，数据丢了一次

有序性，计算机在执行程序时，为了提高性能，编译器和处理器一般会对指令做重排，但有时候为了保证有序性，要禁止指令重排









基于volatile,DCL的高并发下的单例模式










CAS








CAS缺点：
循环时间长，开销大
对于多个变量操作时，无法保证原子性
可能会有ABA问题

ABA：
CAS前提是从主内存中取回数据并进行比较交换，在这个时间差中可能会有问题，
当线程1从主内存中取出一个值A，线程2也从主内存中取出一个值A，线程2对该值进行操作，把它变成B并写回主内存，然后又进行操作，把它又变回A并写回主内存，这时，线程1在CAS操作时发现主内存中值没有变化，然后把线程1操作后的值写回主内存，操作成功
虽然线程1的操作成功，但这个过程是有问题的
解决办法 使用AtomicStampedReference<T>解决

原子引用：
使用AtomicReference<>进行包装












ArrayList、HashSet

ArrayLis、HashSett是线程不安全的，多线程高并发下可能会出现java.util.ConcurrentModificationException
解决方法 :  1,Vector
           2, Collections.synchronizedList(new ArrayList<>());
           3,使用java.util.concurrent包中 CopyAoWriteArrayList




结果是  age---20   personName---------xxx    string------abc



(1)哈希冲突:
数组的长度是5。这时有一个数据是6。按照取模法，计算
6％5，结果是1，那么就把6放到数组下标是1的位置。这时，有个数据是11，按照取模法，11％5＝1，也等于1。那么原来数组下标是1的地方已经有数了，是6。这时又计算出1这个位置，这就是哈希冲突

(2)HashMap的resize 

 当hashmap中的元素越来越多的时候，碰撞的几率也就越来越高（因为数组的长度是固定的），所以为了提高查询的效率，就要对hashmap的数组进行扩容，数组扩容这个操作也会出现在ArrayList中，所以这是一个通用的操作，很多人对它的性能表示过怀疑，不过想想我们的“均摊”原理，就释然了，而在hashmap数组扩容之后，最消耗性能的点就出现了：原数组中的数据必须重新计算其在新数组中的位置，并放进去，这就是resize。 

         那么hashmap什么时候进行扩容呢？当hashmap中的元素个数超过数组大小*loadFactor时，就会进行数组扩容，loadFactor的默认值为0.75，也就是说，默认情况下，数组大小为16，那么当hashmap中元素个数超过16*0.75=12的时候，就把数组的大小扩展为2*16=32，即扩大一倍，然后重新计算每个元素在数组中的位置，而这是一个非常消耗性能的操作，所以如果我们已经预知hashmap中元素的个数，那么预设元素的个数能够有效的提高hashmap的性能。比如说，我们有1000个元素new HashMap(1000), 但是理论上来讲new HashMap(1024)更合适，不过上面annegu已经说过，即使是1000，hashmap也自动会将其设置为1024。 但是new HashMap(1024)还不是更合适的，因为0.75*1000 < 1000, 也就是说为了让0.75 * size > 1000, 我们必须这样new HashMap(2048)才最合适，既考虑了&的问题，也避免了resize的问题。 

(3)key的hashcode与equals方法改写 
Hashmap的key可以是任何类型的对象，例如User这种对象，为了保证两个具有相同属性的user的hashcode相同，我们就需要改写hashcode方法，比方把hashcode值的计算与User对象的id关联起来，那么只要user对象拥有相同id，那么他们的hashcode也能保持一致了，这样就可以找到在hashmap数组中的位置了。如果这个位置上有多个元素，还需要用key的equals方法在对应位置的链表中找到需要的元素，所以只改写了hashcode方法是不够的，equals方法也是需要改写滴~当然啦，按正常思维逻辑，equals方法一般都会根据实际的业务内容来定义，例如根据user对象的id来判断两个user是否相等。
在改写equals方法的时候，需要满足以下三点： 
(1) 自反性：就是说a.equals(a)必须为true。 
(2) 对称性：就是说a.equals(b)=true的话，b.equals(a)也必须为true。 
(3) 传递性：就是说a.equals(b)=true，并且b.equals(c)=true的话，a.equals(c)也必须为true。 










公平、非公平锁
公平锁 ： 多个线程按照申请锁的顺序获取锁
非公平锁：多个线程获取锁的顺序并不是按照申请锁的顺序，可能后申请的比先申请的先获得锁，在高并发情况下，可能出现优先级反转、饥饿现象
new ReentrantLock默认是非公平锁（等同于new ReentrantLock（false）），其非公平锁的优点是吞吐量比较大
synchronized也是非公平锁

可重入锁（递归锁）

什么是可重入锁：线程可以进入任何一个它已经拥有的锁所同步着的代码块。即
一个synchronized修饰的同步方法1中调用了另一个synchronized修饰的同步方法2，他们持有同一个锁。
可重入锁 最大优点是避免死锁
ReentrantLock、synchronized就是可重入锁

自旋锁

是指尝试获取锁的线程不会立即阻塞，而是以循环的方式尝试去获取锁，好处是减少上下文切换的消耗，但是消耗cpu性能

示例：(cas) 略

独占锁（写锁）、共享锁（写锁）、互斥锁

独占锁：指该锁一次只能被一个线程持有，ReentrantLock和synchroized都是独占锁
共享锁：指该锁可以被多个线程持有，ReentrantReadWriteLock的读是共享锁，写是独占锁，读锁的共享锁可保证高并发下读是非常高效的，读写、写读、写写的过程是互斥的
如果有一个线程写共享资源，其他线程不能对其读或写



CountDownLatch、CyclicBarrier、Semaphone



1、CountDownLatch
  CountDownLatch可以让一些线程阻塞，直到一些线程完成一些操作后才被唤醒
  主要有两个方法，一个是使线程阻塞，await()方法，一个是使计数器减一，直到计数器到0 ，其阻塞的线程会被唤醒，继续执行操作


2、CyclicBarrier
可循环的屏障，一组线程到达屏障时阻塞，直到所有线程达到这个屏障，屏障才会消失，继续操作


3、Semaphone
信号量，两个目的，1、多个共享资源的互斥使用，2、并发线程数的控制
、

阻塞队列 （BlockingQueue接口，和List接口是Collection下的接口）是一个队列
在多线程情况下，在某些情况下会挂起线程，即阻塞，一旦条件满足，挂起的线程会被唤醒
使用BlockingQueue ，不需要关系什么时候需要阻塞线程，什么时候需要唤醒线程。
使用的地方：生产者消费者模式、线程池、消息中间件

线程1向阻塞队列中添加元素，线程2从阻塞队列中移除元素
当阻塞队列是空时，从队列中获取元素将会被阻塞，
当阻塞队列是满时，向队列中添加元素将会被阻塞。




1、ArrayBlockingQueue




add          offer        put    offer(obj , timeout , timeUtil)
remove         poll        take    poll(timeout , timeUtil)
element        peek
可能会报异常  不报异常    会阻塞      等待指定时间

ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);


2、SynchronousQueue
SynchronousQueue没有容量，和其他阻塞队列不同，SynchronousQueue是一个不存储元素的阻塞队列，每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然

synchronize 和Lock区别
1、原始构成
(1)synchronized是关键字，属于JVM层面  monitorenter、monitorexit 底层通过monito等方法也是r对象完成，其wait、notify也是依赖montinor对象 只有在同步代码块或者方法中才能调用
(2)lock是具体类 是api层面
2、使用方法
(1)synchronized不需要手动释放锁，代码执行完后自动释放
(2)reentrantLock需要手动释放，如果没有释放可能导致死锁，需要lock（）unlock（）配合 try finally完成
3、是否可以中断
(1)synchornized不可中断，除非异常或者正常执行完毕
(2)ReentrantLock可以中断，设置超时事件 trylock（Long timeout，TimeUnit unit）  LockInteruptibly（）放代码块中 调用interrupt（）方法中断
4、加锁是否公平
(1)synchornized是非公平锁
(2)ReentrantLock默认非公平锁，构造方法传布尔，true是公平锁，false是非公平锁
5、锁绑定条件Condition
synchronized没有，只能随机唤醒一个，或者唤醒所有的
ReentrantLock 用来实现分组唤醒需要唤醒的线程，精确唤醒

事务的四个特性

① Atomic（原子性）：事务中包含的操作被看做一个逻辑单元，这个逻辑单元中的操作要么全部成 功，要么全部失败。
② Consistency（一致性）：事务完成时，数据必须处于一致状态，数据的完整性约束没有被破坏，事务在执行过程中发生错误，会被回滚（Rollback）到事务开始前的状态，就像这个事务从来没    有执行过一样。

③ Isolation（隔离性）：事务允许多个用户对同一个数据进行并发访问，而不破坏数据的正确性 和完整性。同时，并行事务的修改必须与其他并行事务的修改相互独立。

 4.Durability（持久性）：事务结束后，事务处理的结果必须能够得到固化。



线程池
线程池主要是控制运行线程的数量，处理过程中将任务放入队列，当线程创建后启动这些任务，如果线程数量超出了最大线程数量，超出的线程排队等候，等其他线程执行完毕，再从队列中去除任务执行

特点：
线程复用，控制最大线程并发数，管理线程
降低资源消耗，通过重复利用已创建的线程，避免线程创建和销毁的消耗
提高相应速速，当任务到达时，任务不需要等待线程的创建就可以执行
提高线程的管理性，线程是稀缺资源，如果无限制创建，会消耗系统资源，降低系统稳定性，使用线程池可以进行统一分配，调优和监控

常见线程池：
1、Executors.newFixedThreadPool（Int num）；固定长度的线程池 其创建的corePoolSize和maximumPoolSize大小一样，使用LinkedBlockingQueue
2、Executors.newSingleThreadExecutor()；单线程线程池，其创建的corePoolSize和maximumPoolSize大小是1，，使用LinkedBlockingQueue
3、Executors.newCachedThreadPool(); 可缓存的线程池，其创建的corePoolSize是0，maximumPoolSize是Integer.MAX_VALUE，空闲60s后销毁 使用SynchronousQueue，来了任务就创建线程

以上三种线程池工作中用哪个？
哪个都不用，一般自定义线程池，Excutors返回的线程池，FixedThreadPool和SingleThreadPool允许请求的队列长度为Integer.MAX_VALUE，可能堆积大量请求，导致OOM，CachedThreadPool和ScheduledThreadPool允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量线程，导致OOM



线程池七大参数：
1、corePollSize 线程池中常驻核心线程数，创建了线程池后，当有任务过来，就会安排线程池中线程去执行，当线程池中的线程数达到corePoolSize后，就会把到达的任务放到缓存队列中
2、maximumPoolSize 线程池能够容纳同时执行的最大线程数，值必须大于等于1
3、keepAliveTime 空闲线程的最大存活时间，只有在线程池中的数量大于corePoolSize时，该参数才会起作用，当线程池数量超过corePoolSize时，当空闲时间超过keepAliveTime时，空闲线程会被销毁，直到线程池中线程数量不大于corePoolSize为止
4、unit  keepAliveTime的单位
5、workQueue 任务队列，被提交但未执行的任务
6、threadFactory 生成线程池中工作线程的线程工厂，用于创建线程，一般用默认的
7、handler 拒绝策略，当队列满了，并且哦给你做线程大于等于线程池的最大线程数时如何来拒绝请求执行的runnable的策略





线程池底层原理：



线程池的拒绝策略：
当等待队列中排满了，并且线程池中也到达最大线程数，这是需要拒绝策略机制处理问题

1、AbortPolicy（默认） 直接抛出异常
2、CallerRunsPolicy 调用者运行，将任务回退到调用者执行，降低新任务的流量
3、DiscardOldestPolicy 抛弃队列中等待最长的任务，然后把新任务加入队列中
4、DiscardPolicy 直接丢弃任务，不处理不抛异常
4种策略都实现了RejectedExcutionHandler接口

线程池配置合理线程数
1、CPU密集型 即该任务需要大量的运算，没有阻塞，CPU一直全速运行，所以尽可能减少线程数量， CPU核数+1个线程的线程池
2、IO密集型 即需要大量的IO操作，即大量的阻塞，所以尽可能增加线程以加速程序
 i、核数*2
ii、核数/1-阻塞系数     阻塞系数一般为（0.8-0.9）






redis的Pipeline 
pipeline通过减少客户端与redis的通信次数来实现降低往返延时时间，而且Pipeline 实现的原理是队列，而队列的原理是时先进先出，这样就保证数据的顺序性。

死锁
 死锁是指两个或两个以上线程在执行过程中，因争抢资源而造成的一种互相等待的现象


产生死锁原因：
1、系统资源不足
2、进行推进顺序不合适
3、资源分配不当

















package cn.clj.zchao.singleton;

/**
 * 〈基于volatile,DCL的高并发下的单例模式〉
 *
 * @author 22902
 * @create 2019/6/11
 */
public class SingletonDemo {

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "实例化");
    }
    //使用volatile修饰,禁止指令重排
    private static volatile SingletonDemo singletonDemo = null;

    //高并发下,使用
    // DCL double check lock 双端检锁机制
    public static SingletonDemo getSingletonDemo() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                getSingletonDemo();
            }, "thread" + i).start();
        }
    }

}

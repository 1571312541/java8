package cn.clj.zchao.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 〈堆溢出示例〉
 *          java.lang.OutOfMemoryError: Metaspace
 *  java8以后 使用元空间代替永久代
 *  Metaspace是方法区在hopspot中的实现，它与持久代最大的区别在于：Metaspace不再虚拟机内存中，而是在本地内存
 *
 *  在元空间中存储了:1、虚拟机加载的类的信息 2、常量池 3、静态变量  4、编译后的代码
 *
 *  模拟元空间溢出，不断生成类放进元空间，当类占据的空间超过了元空间大小时，会出异常
 *
 *  VM-option： -XX:MaxMetaspaceSize=10m -XX:MetaspaceSize=10m
 *
 *
 *
 *
 * @author zc
 * @create 2019/7/19
 */
public class OutOfMemoryError_metaspace {

    static class MyClass{}

    public static void main(String[] args) {
        MyClass o1 = new MyClass();
        int i = 0;
        try {
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(o1.getClass());
                enhancer.setUseCache(false);
                enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o,objects));
                enhancer.create();
            }
        } catch (Error e) {
            System.out.println(i);
            e.printStackTrace();
        }


    }


}

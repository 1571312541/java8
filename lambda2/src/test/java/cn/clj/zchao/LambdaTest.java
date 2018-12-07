package cn.clj.zchao;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 〈java8提供的函数式接口测试〉
 *  java8提供四个基础的函数接口
 *  Consumer<T> 消费型接口    void accept（T t）、
 *  Supplier<T> 供给型接口    T get（）、
 *  Function<R,T> 函数型接口  R apply(T t)、
 *  Predicact<T> 断言型接口   boolean test（T t）
 * @author 22902
 * @create 2018/12/7
 */
public class LambdaTest {

    /**
     * 测试消费型接口 Consumer
     */
    @Test
    public void test1() {
        method1("啦啦啦",(s) -> System.out.println(s));
    }

    public void method1(String str, Consumer con){
        con.accept(str);
    }

    /**
     * 测试消费型接口 Supplier
     */
    @Test
    public void test2() {
        Integer num = method2(200, () -> 300);
        System.out.println(num);
    }

    public Integer method2(Integer num , Supplier<Integer> sup){
        Integer nu = sup.get();
        return  nu + num;

    }



}

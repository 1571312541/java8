package cn.clj.zchao;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * 〈 〉
 *
 * @author 22902
 * @create 2018/12/7
 */
public class LambdaTest {

    int num = 10; //jdk1.7时，必须时final

    @Test
    public void test1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("111111111"+num);
            }
        };
        r.run();
    }

    /**
     * 一、
     * 1、使用lambda  无参数、无返回值
     */
    @Test
    public void test2() {
        Runnable r = () -> System.out.println("22222222"+num);
        r.run();
    }
    /**
     * 2、使用lambda  有参数、无返回值
     */
    @Test
    public void test3() {

        Consumer consumer = (d) -> System.out.println(d);
        consumer.accept("1111111");

    }
    /**
     * 使用lambda  有参数、无返回值
     * 如果有一个参数，小括号可以省略
     */
    @Test
    public void test4() {

        Consumer consumer = d -> System.out.println(d);
        consumer.accept("1111111");

    }
    /**
     * 3、使用lambda  有参数、有返回值
     *  lambda体中有多个操作用大括号
     */
    @Test
    public void test5() {

        Comparator<Integer> comparator = (a,b) -> {
            System.out.println("1111111111");
            return Integer.compare(b, a);
        };
        TreeSet<Integer> t = new TreeSet<>(comparator);
        t.add(10);
        t.add(3);
        t.add(22);
        t.add(11);
        System.out.println(t);

    }
    /**
     * 4、使用lambda  有参数、有返回值
     * 如果lambda体中只有一条语句，return和大括号可以省略
     */
    @Test
    public void test6() {

        Comparator<Integer> comparator = (a,b) -> Integer.compare(b, a);

        TreeSet<Integer> t = new TreeSet<>(comparator);
        t.add(11);
        t.add(3);
        t.add(22);
        t.add(12);
        System.out.println(t);

    }
    /**
     * 5、使用lambda
     * Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
     */
    @Test
    public void test7() {
        Comparator<Integer> comparator = (Integer a,Integer b) -> Integer.compare(b, a);
        Comparator<Integer> comparator2 = (a,b) -> Integer.compare(b, a);
    }

    /**
     *
     * 二、Lambda 表达式需要“函数式接口”的支持
     * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
     * 可以检查是否是函数式接口
     */
    @Test
    public void test8() {
        Integer nu = jisuan(100, (x) -> (100 + x));
        System.out.println(nu);

        Integer nu2 = jisuan(100, (x) -> (x * 10));
        System.out.println(nu2);
    }

    public Integer jisuan(Integer num,MyOperation mo){
        return mo.operation(num);
    }



}

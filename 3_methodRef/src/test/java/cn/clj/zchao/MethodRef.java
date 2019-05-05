package cn.clj.zchao;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 〈方法引用：若lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 *     可以理解为方法引用是lambda表达式的另一种表现形式
 *   三种语法格式：
 *     1、 对象 :: 实例方法名
 *     2、 类 :: 静态方法名
 *     3、 类 :: 实例方法名
 *
 *   注意：lambda 体中调用方法的参数列表和返回值类型，要与函数接口的参数列表和返回值类型相同
 * 〉      lambda 参数列表中第一个参数是实例方法的调用者，并且第二个参数是实例方法的参数时，可以使用3、 类 :: 实例方法名
 *
 */
public class MethodRef {

    // 1、对象 :: 实例方法名
    @Test
    public void test1() {
        Consumer<String> cu = (s) -> System.out.println(s);

        Consumer<String> cu2 = System.out::println;
        cu2.accept("哈哈哈哈");

    }

    @Test
    public void test2() {
        User user = new User();
        Supplier<String> su = () -> user.getName();

        Supplier<String> su2 = user :: getName;
        String s = su2.get();
        System.out.println(s);
    }

    // 2、 类 :: 静态方法名
    @Test
    public void test3() {
        Comparator<Integer> c = (x,y) -> Integer.compare(x,y);

        Comparator<Integer> c2 = Integer::compareTo;
        c2.compare(15,14);

    }

    // 3、 类 :: 实例方法名
    @Test
    public void test4() {
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);

        BiPredicate<String,String> bp2 = String::equals;
        boolean b = bp2.test("dfdfd", "dfdfd");
        System.out.println(b);

    }

}

package cn.clj.zchao;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * 〈构造器引用
 *      需要调用的构造器参数列表要与函数式接口中抽象方法的参数列表相同
 * 〉
 *
 * @author zc
 * @create 2018/12/12
 */
public class ConstructorRefTest {
    @Test
    public void test1(){

        Supplier<User> su = () -> new User();

        Supplier<User> su2 = User::new;
        User user = su2.get();
        System.out.println(user);

    }


}

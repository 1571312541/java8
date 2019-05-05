package cn.clj.zchao;

/**
 * 〈函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
 *   可以检查是否是函数式接口〉
 *
 * @author zc
 * @create 2018/12/7
 */
@FunctionalInterface
public interface MyPredicate <T> {

    boolean myCompare(T t);


}

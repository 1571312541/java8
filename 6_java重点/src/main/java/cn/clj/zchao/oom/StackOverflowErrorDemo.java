package cn.clj.zchao.oom;

/**
 * 〈栈溢出错误示例〉
 *               --> Exception
 *              -
 *  Throwable --
 *              -             --> StackOverflowError
 *               --> Error --
 *                            --> OutOfMemoryError
 *
 *    1.exception和error都是继承了throwable类，在java中只有throwable类型的
 *      实例才可以被抛出(throw)或捕获(catch)，它是异常处理机制的基本组成类型
 *    2.exception和error体现了对不同异常情况的分类。exception是程序正常运行中，
 *      可以预料的意外情况，并且应该被捕获，进行相应的处理
 *    3.error是指在正常情况下，不大可能出现的情况，大部分的error都会导致程序
 *      处于非正常的、不可恢复的状态。既然是非正常情况，所以不便于也不需要捕获，
 *      常见的比如OutOfMemoryError之类，都是Error的子类
 *    4.Exception又分为可检查异常和不可检查异常，可检查异常必须处理，这是编译
 *      期检查的一部分。不可检查异常就是运行时异常，类似NullPointerException，
 *      通常是可以编码避免的逻辑错误，具体可以根据需要来判断是否需要捕获，并不	会在编译期强制要求
 *
 *  1、java.lang.StackOverflowError
 *  2、java.lang.OutOfMemoryError:java heap space
 *  3、java.lang.OutOfMemoryError:GC overhead limit exceeded
 *  4、java.lang.OutOfMemoryError:Direct buffer memory
 *  5、java.lang.OutOfMemoryError:unable to create new native thread
 *  6、java.lang.OutOfMemoryError:Metaspace
 * @author zc
 * @create 2019/7/18
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        //java.lang.StackOverflowError  递归调用
        main(args);


    }
}

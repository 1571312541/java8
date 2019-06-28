package cn.clj.zchao.atomicReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 〈原子引用,使用AtomicReference 自定义原子引用类型〉
 *
 * @author 22902
 * @create 2019/6/12
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private String name;
    private Integer age;
}
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User z3 = new User("z3", 20);
        User li4 = new User("li4", 25);

        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(z3);
        System.out.println(reference.compareAndSet(z3, li4) + "  " + reference.get());
        System.out.println(reference.compareAndSet(z3, li4) + "  " + reference.get());
    }



}

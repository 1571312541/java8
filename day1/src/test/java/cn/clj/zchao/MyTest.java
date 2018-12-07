package cn.clj.zchao;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * 〈〉
 *
 * @author 22902
 * @create 2018/12/7
 */
public class MyTest {

    @Test
    public void test1(){
        System.out.println(new User("张三",20,170));
    }
    @Test
    public void test2(){
        List<User> list = getList();
        System.out.println(list);
    }

    /**
     * 使用内部类
     */
    @Test
    public void test3(){
        Comparator co = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2,o1);
            }
        };
        TreeSet<Integer> t = new TreeSet<>(co);
        t.add(10);
        t.add(3);
        t.add(22);
        t.add(11);
        System.out.println(t);

    }

    /**
     * 简化test3
     * 使用lambda
     */
    @Test
    public void test4() {
        Comparator<Integer> co = (x,y)->Integer.compare(y,x);

        TreeSet<Integer> t = new TreeSet<>(co);
        t.add(10);
        t.add(3);
        t.add(22);
        t.add(11);
        System.out.println(t);

    }

    /**
     * 用户年龄大于30
     */
    @Test
    public void test5() {
        List<User> list = getList();
        List<User> userlist = new ArrayList<>();
        for (User user : list) {
            if (user.getAge()>30){
                userlist.add(user);
            }
        }
        for (User user : userlist) {
            System.out.println(user);
        }
    }


    /**
     * 用户年龄大于30
     * 优化test5  方式1
     * 策略设计模式
     */
    @Test
    public void test6() {
        List<User> list = getList();
        List<User> users = filterUser(list, new MyPredicateImpl());
        for (User user : users) {
            System.out.println(user);
        }
    }

    public List<User> filterUser(List<User> list,MyPredicate<User> t){
        List<User> userList = new ArrayList<>();
        for (User user : list) {
            if (t.myCompare(user)){
                userList.add(user);
            }
        }
        return userList;
    }

    /**
     * 用户年龄大于30
     * 优化test5  方式2
     * 匿名内部类
     */
    @Test
    public void test7() {
        List<User> list = getList();
        List<User> users = filterUser(list, new MyPredicate<User>() {
            @Override
            public boolean myCompare(User user) {
                return user.getAge()>30;
            }
        });
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 用户年龄大于30
     * 优化test5  方式3
     *  lambda
     */
    @Test
    public void test8() {
        List<User> list = getList();
        List<User> userslist = filterUser(list, (obj) -> obj.getAge() > 30);
        userslist.forEach(System.out::println);

    }



    public List<User> getList(){
        List<User> list = new ArrayList<>();
        list.add(new  User("张三",20,170));
        list.add(new  User("里斯",31,160));
        list.add(new  User("王五",26,177));
        list.add(new  User("赵六",33,180));
        list.add(new  User("天气",40,166));
        return list;
    }



}

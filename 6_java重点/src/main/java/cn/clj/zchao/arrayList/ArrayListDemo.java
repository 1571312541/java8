package cn.clj.zchao.arrayList;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 〈ArrayList 是线程不安全的〉
 * 〈HashSet是线程不安全的〉
 * 〈HashMap 是线程不安全的〉
 *
 * @author 22902
 * @create 2019/6/12
 */
public class ArrayListDemo {

    /**
     *
     * 出现异常: java.util.ConcurrentModificationException
     * 出现原因: 多线程高并发
     *
     * 解决方法 : 1,Vector
     *            2, Collections.synchronizedList(new ArrayList<>());
     *            3,使用java.util.concurrent包中 CopyAoWriteArrayList
     */
    public static void main(String[] args) {
//        arrayListTeat();
//        hashSetTest();

        hashMapTest();

    }

    /**
     * HashMap
     */
    private static void hashMapTest() {
        Map<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                hashMap.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(hashMap);
            },"thread"+i).start();
        }

        Map<String, String> hashMap2 = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                hashMap2.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(hashMap2);
            },"thread"+i).start();
        }
        Map<String, String> hashMap3 = Collections.synchronizedMap(new HashMap<>());
    }


    /**
     *  ArrayList
     */
    private static void arrayListTeat() {
        //多线程下
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },"thread"+i).start();
        }
        //上面程序会出现 java.util.ConcurrentModificationException
        Vector<String> strings1 = new Vector<>();

        List<String> ss = Collections.synchronizedList(new ArrayList<>());

        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                copyOnWriteArrayList.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(copyOnWriteArrayList);
            },"thread"+i).start();
        }
    }
    /**
     *  HashSet  同上
     *  new CopyOnWriteArraySet<>(); 底层实际是 new CopyOnWriteArrayList<>();
     */
    private static void hashSetTest() {
        //多线程下
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },"thread"+i).start();
        }

        Set<String> set2 = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set2.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set2);
            },"thread"+i).start();
        }

        CopyOnWriteArraySet<String> set3 = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set3.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set3);
            },"thread"+i).start();
        }

        HashSet<String> hashSet = new HashSet<>();
        //hashSet底层是HashMap,当hashSet在add一个元素时,hashSet.add("a"),HashMap.push是两个键值对
        //底层hashSet做了处理,键一直是a 值是常量 new Object
    }




}
package cn.clj.zchao.gc;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 〈WeakHashMap示例〉
 * 当使用WeakHashMap时，把key置空，gc后，会被回收
 * @author zc
 * @create 2019/7/15
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        hashMapTest();
        System.out.println("=================");
        weakHashMapTest();
    }

    private static void weakHashMapTest() {
        WeakHashMap<String, String> map = new WeakHashMap<>(4);
        String key = new String("2");
        String value = "weakhashmap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);//gc后，会被回收
    }

    private static void hashMapTest() {
        HashMap<String, String> map = new HashMap<>(4);
        String key = new String("1");
        String value = "hashmap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);//gc后，不会被回收

    }

}

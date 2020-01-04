package com.lc.algorithm;

import org.junit.Test;

import java.util.Map;

public class NC0007LruTest {
    @Test
    public void testLur() throws Exception {
        //指定缓存最大容量为4
        Map<Integer,Integer> map=new NC0007Lru<>(4);
        map.put(9,3);
        map.put(7,4);
        map.put(5,9);
        map.put(3,4);
        map.get(9);
        map.put(6,6);
        //总共put了5个元素，超过了指定的缓存最大容量
        //遍历结果
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            System.out.println(integerIntegerEntry.getKey());
        }
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }
}
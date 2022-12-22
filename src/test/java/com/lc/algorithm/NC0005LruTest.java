package com.lc.algorithm;

import org.junit.Test;

import java.util.Map;

public class NC0005LruTest {
    @Test
    public void testLur() throws Exception {
        // 指定缓存最大容量为4
        Map<Integer, String> map = new NC0005Lru<>(4);
        // 先放满4个为净
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        // 访问元素，把最近访问的元素移动到链表尾部
        map.get(1);
        // 再放一个，并淘汰一个最近最少使用的（链表头节点）
        map.put(5, "e");
        // 遍历结果
        System.out.print("遍历元素（链表从头至尾）：");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.print("{" + entry.getKey() + ":" + entry.getValue() + "} ");
        }
        System.out.print("\n遍历元素（链表从头至尾）：");
        map.forEach((key, value) -> {
            System.out.print("{" + key + ":" + value + "} ");
        });
    }
}
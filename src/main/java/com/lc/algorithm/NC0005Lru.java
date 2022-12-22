package com.lc.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class NC0005Lru<K, V> extends LinkedHashMap<K, V> {
    /**
     * 定义缓存的容量
     */
    private int capacity;
    private static final long serialVersionUID = 1L;

    //带参数的构造器
    NC0005Lru(int capacity) {
        //调用LinkedHashMap的构造器，传入以下参数
        super(new Float(capacity / 0.75f).intValue() + 1, 0.75f, true);
        //传入指定的缓存最大容量
        this.capacity = capacity;
    }

    //实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        System.out.println("最老的元素={" + eldest.getKey() + ":" + eldest.getValue() + "}");
        return size() > capacity;
    }
}

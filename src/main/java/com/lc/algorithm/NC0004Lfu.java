package com.lc.algorithm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NC0004Lfu<K, V> extends HashMap<K, V> {
    private static final int DEFAULT_MAX_SIZE = 16;
    private int maxSize = DEFAULT_MAX_SIZE;

    Map<K, HitRate> km = new HashMap<K, HitRate>();

    public NC0004Lfu() {
        this(DEFAULT_MAX_SIZE);
    }

    public NC0004Lfu(int maxSize) {
        super(maxSize);
        this.maxSize = maxSize;
    }

    @Override
    public V get(Object key) {
        V v = super.get(key);
        if (v != null) {
            HitRate hitRate = km.get(key);
            hitRate.hitCount += 1;
            hitRate.atime = System.nanoTime();
        }
        return v;
    }

    @Override
    public V put(K key, V value) {
        while (km.size() >= maxSize) {
            K k = getLeastFrequentKey();
            km.remove(k);
            this.remove(k);
        }
        V v = super.put(key, value);
        km.put(key, new HitRate(key, 1, System.nanoTime()));
        return v;
    }

    private K getLeastFrequentKey() {
        // 这种方式每次找要淘汰的Key时间复杂度都是O(n)
        // 维护一个逆序数组，可以将时间复杂度降低到O(logn)
        HitRate min = Collections.min(km.values());
        return min.key;
    }


    class HitRate implements Comparable<HitRate> {
        K key;
        Integer hitCount; // 命中次数
        Long atime; // 上次命中时间

        public HitRate(K key, Integer hitCount, Long atime) {
            this.key = key;
            this.hitCount = hitCount;
            this.atime = atime;
        }

        @Override
        public int compareTo(HitRate o) {
            int hr = hitCount.compareTo(o.hitCount);
            return hr != 0 ? hr : atime.compareTo(o.atime);
        }
    }
}

package com.lc.algorithm;

import com.lc.algorithm.NC0007Lfu;
import org.junit.Test;

public class NC0007LfuTest {

    @Test
    public void testLfu() throws Exception {
        NC0007Lfu<String, String> cache = new NC0007Lfu<>(3);
        cache.put("a", "a");
        cache.put("b", "b");
        cache.put("c", "c");

        cache.get("a");
        cache.get("a");

        cache.put("d", "d");
        cache.get("d");
        cache.get("c");
        cache.put("e", "e");

        for (String key : cache.keySet()) {
            System.out.println(key);
        }
    }
}
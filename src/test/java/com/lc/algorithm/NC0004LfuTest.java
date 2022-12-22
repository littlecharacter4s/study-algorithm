package com.lc.algorithm;

import org.junit.Test;

public class NC0004LfuTest {

    @Test
    public void testLfu() throws Exception {
        NC0004Lfu<String, String> cache = new NC0004Lfu<>(3);
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
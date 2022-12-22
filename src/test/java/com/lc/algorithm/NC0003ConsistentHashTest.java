package com.lc.algorithm;

import org.junit.Test;

public class NC0003ConsistentHashTest {
    @Test
    public void testGetServer() throws Exception {
        String[] datas = {"aaa", "bbb", "ccc"};
        NC0003ConsistentHash consistentHash = new NC0003ConsistentHash();
        for (String data : datas) {
            System.out.println("[" + data + "]的hash值为" + NC0003ConsistentHash.getHash(data) + ", 被路由到结点[" + consistentHash.getServer(data) + "]");
        }
    }
}
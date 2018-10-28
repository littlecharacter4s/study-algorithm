package com.lc.algorithm;

import org.junit.Test;

public class NaaafConsistentHashTest {
    @Test
    public void testGetServer() throws Exception {
        String[] datas = {"aaa", "bbb", "ccc"};
        NaaafConsistentHash consistentHash = new NaaafConsistentHash();
        for (String data : datas) {
            System.out.println("[" + data + "]的hash值为" + NaaafConsistentHash.getHash(data) + ", 被路由到结点[" + consistentHash.getServer(data) + "]");
        }
    }
}
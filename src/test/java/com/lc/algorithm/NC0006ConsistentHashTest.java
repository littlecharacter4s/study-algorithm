package com.lc.algorithm;

import com.lc.algorithm.nc.NC0006ConsistentHash;
import org.junit.Test;

public class NC0006ConsistentHashTest {
    @Test
    public void testGetServer() throws Exception {
        String[] datas = {"aaa", "bbb", "ccc"};
        NC0006ConsistentHash consistentHash = new NC0006ConsistentHash();
        for (String data : datas) {
            System.out.println("[" + data + "]的hash值为" + NC0006ConsistentHash.getHash(data) + ", 被路由到结点[" + consistentHash.getServer(data) + "]");
        }
    }
}
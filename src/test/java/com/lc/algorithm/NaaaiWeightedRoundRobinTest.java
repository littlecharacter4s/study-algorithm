package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NaaaiWeightedRoundRobinTest {
    @Test
    public void testGetServer() throws Exception {
        List<NaaaiWeightedRoundRobin.Server> serverList = new ArrayList<>();
        serverList.add(new NaaaiWeightedRoundRobin.Server(1, "server-1"));
        serverList.add(new NaaaiWeightedRoundRobin.Server(3, "server-2"));
        serverList.add(new NaaaiWeightedRoundRobin.Server(5, "server-3"));
        NaaaiWeightedRoundRobin naaaiWeightedRoundRobin = new NaaaiWeightedRoundRobin(serverList);
        for (int i = 0; i < 9; i++) {
            System.out.println(JSON.toJSONString(naaaiWeightedRoundRobin.getServer1()));
        }
        System.out.println("--------------------------------------------------------");
        serverList.clear();
        serverList.add(new NaaaiWeightedRoundRobin.Server(1, "server-1"));
        serverList.add(new NaaaiWeightedRoundRobin.Server(2, "server-2"));
        naaaiWeightedRoundRobin.reinitialization(serverList);
        for (int i = 0; i < 9; i++) {
            System.out.println(JSON.toJSONString(naaaiWeightedRoundRobin.getServer1()));
        }
        System.out.println("--------------------------------------------------------");
        serverList.clear();
        serverList.add(new NaaaiWeightedRoundRobin.Server(1, "server-1"));
        serverList.add(new NaaaiWeightedRoundRobin.Server(1, "server-2"));
        serverList.add(new NaaaiWeightedRoundRobin.Server(7, "server-3"));
        naaaiWeightedRoundRobin.reinitialization(serverList);
        for (int i = 0; i < 9; i++) {
            System.out.println(JSON.toJSONString(naaaiWeightedRoundRobin.getServer1()));
        }
    }
}
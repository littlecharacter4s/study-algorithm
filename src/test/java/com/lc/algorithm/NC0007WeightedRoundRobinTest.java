package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NC0007WeightedRoundRobinTest {
    @Test
    public void testGetServer() throws Exception {
        List<NC0007WeightedRoundRobin.Server> serverList = new ArrayList<>();
        serverList.add(new NC0007WeightedRoundRobin.Server(1, "server-1"));
        serverList.add(new NC0007WeightedRoundRobin.Server(3, "server-2"));
        serverList.add(new NC0007WeightedRoundRobin.Server(5, "server-3"));
        NC0007WeightedRoundRobin weightedRoundRobin = new NC0007WeightedRoundRobin(serverList);
        for (int i = 0; i < 9; i++) {
            System.out.println(JSON.toJSONString(weightedRoundRobin.getServer1()));
        }
        System.out.println("--------------------------------------------------------");
        serverList.clear();
        serverList.add(new NC0007WeightedRoundRobin.Server(1, "server-1"));
        serverList.add(new NC0007WeightedRoundRobin.Server(2, "server-2"));
        serverList.add(new NC0007WeightedRoundRobin.Server(2, "server-3"));
        weightedRoundRobin.reinitialization(serverList);
        for (int i = 0; i < 15; i++) {
            System.out.println(JSON.toJSONString(weightedRoundRobin.getServer1()));
        }
        System.out.println("--------------------------------------------------------");
        serverList.clear();
        serverList.add(new NC0007WeightedRoundRobin.Server(1, "server-1"));
        serverList.add(new NC0007WeightedRoundRobin.Server(1, "server-2"));
        serverList.add(new NC0007WeightedRoundRobin.Server(7, "server-3"));
        weightedRoundRobin.reinitialization(serverList);
        for (int i = 0; i < 9; i++) {
            System.out.println(JSON.toJSONString(weightedRoundRobin.getServer1()));
        }
    }
}
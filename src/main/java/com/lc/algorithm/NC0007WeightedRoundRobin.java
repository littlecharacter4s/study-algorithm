package com.lc.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Nginx加权轮询算法的Java实现
 * 两点：
 * 1、每轮取当前权重最大的节点；
 * 2、每轮每个节点的当前节点权重都要加上初始权重，就是为了每轮下来，所有节点的当前节点之和等于所有节点的初始权重之和
 */
public class NC0007WeightedRoundRobin {
    private volatile List<Server> serverList = new ArrayList<>();
    private volatile Server selectServer = null;
    private volatile int sumWeight;

    public NC0007WeightedRoundRobin(List<Server> serverList) {
        this.serverList = serverList;
        sumWeight = 0;
        for (Server server : serverList) {
            sumWeight += server.getWeight();
        }
    }

    public void reinitialization(List<Server> serverList) {
        this.serverList = serverList;
        sumWeight = 0;
        for (Server server : serverList) {
            sumWeight += server.getWeight();
            server.setCurrentWeight(server.getWeight());
        }
    }

    public synchronized Server getServer1() {
        // 初始权重的总权重，其实可以设置成全局的，有节点增、删、改，重新计算
        int totalWeight = 0;
        Server maxServer = null;
        for (Server server : serverList) {
            totalWeight += server.getWeight();
            // 当前权重 = 当前权重 + 原始权重
            server.setCurrentWeight(server.getCurrentWeight() + server.getWeight());
            // 保存 当前权重 最大的节点
            if (maxServer == null || maxServer.getCurrentWeight() < server.getCurrentWeight()) {
                maxServer = server;
            }
        }
        System.out.println("总权重：" + totalWeight);
        // 被选中的节点，当前权重 = 当前权重 - 总权重
        maxServer.setCurrentWeight(maxServer.getCurrentWeight() - totalWeight);
        return maxServer;
    }

    public synchronized Server getServer2() {
        Server maxServer = null;
        for (Server server : serverList) {
            if (Objects.equals(selectServer, server)) {
                continue;
            }
            if (maxServer == null && server.getCurrentWeight() != 0) {
                maxServer = server;
            }
            if (maxServer != null && maxServer.getCurrentWeight() < server.getCurrentWeight()) {
                maxServer = server;
            }
        }
        if (maxServer != null) {
            selectServer = maxServer;
        }
        selectServer.setCurrentWeight(selectServer.getCurrentWeight() - 1);
        if (--sumWeight == 0) {
            reinitialization(serverList);
        }
        return selectServer;
    }

    static class Server {
        /**
         * 初始权重（保持不变）
         */
        private final int weight;
        /**
         * 服务名
         */
        private final String serverName;
        /**
         * 当前权重
         */
        private int currentWeight;

        public Server(int weight, String serverName) {
            this.weight = weight;
            this.serverName = serverName;
            this.currentWeight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public String getServerName() {
            return serverName;
        }

        public int getCurrentWeight() {
            return currentWeight;
        }

        public void setCurrentWeight(int currentWeight) {
            this.currentWeight = currentWeight;
        }
    }
}

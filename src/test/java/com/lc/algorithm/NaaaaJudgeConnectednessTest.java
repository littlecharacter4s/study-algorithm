package com.lc.algorithm;

import org.junit.Test;

import java.util.Random;

public class NaaaaJudgeConnectednessTest {
    @Test
    public void testIsConnected() throws Exception {
        int limit = 10;
        int size = 10;
        int[][] lines = new int[size][2];
        boolean result = true;
        while (result) {
            for (int i = 0; i < size; i++) {
                lines[i][0] = new Random().nextInt(limit - 1) + 1;
                lines[i][1] = new Random().nextInt(limit - 1) + 1;
            }
            System.out.print("[");
            for (int i = 0; i < lines.length; i++) {
                System.out.print("[");
                System.out.print(lines[i][0]);
                System.out.print(",");
                System.out.print(lines[i][1]);
                System.out.print("]");
                if (i < lines.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]\n");
            result = new NaaaaJudgeConnectedness().isConnected(lines, limit);
            System.out.println("连通？" + (result ? "是" : "否"));
        }
    }
}
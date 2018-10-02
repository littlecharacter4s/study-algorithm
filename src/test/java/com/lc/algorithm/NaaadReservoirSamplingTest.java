package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Random;

public class NaaadReservoirSamplingTest {
    @Test
    public void sampling() throws Exception {
        final int N = 1000;
        final int K = 100;
        int[] pool = new int[N];
        for (int i = 0; i < N; i++) {
            pool[i] = new Random().nextInt(N);
        }
        System.out.println("蓄水池：");
        new NaaacSort().quickSort(pool, 0, pool.length - 1);
        System.out.println(JSON.toJSONString(pool));
        System.out.println("样本：");
        int[] sample = new NaaadReservoirSampling().sampling(pool, K);
        new NaaacSort().mergeSortIteration(sample);
        System.out.println(JSON.toJSONString(sample));
    }
}
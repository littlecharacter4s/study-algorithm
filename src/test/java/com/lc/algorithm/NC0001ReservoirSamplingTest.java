package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import com.lc.algorithm.sort.NC0001Sort;
import org.junit.Test;

import java.util.Random;

public class NC0001ReservoirSamplingTest {
    @Test
    public void sampling() throws Exception {
        final int N = 1000;
        final int K = 100;
        int[] pool = new int[N];
        for (int i = 0; i < N; i++) {
            pool[i] = new Random().nextInt(N);
        }
        System.out.println("蓄水池：");
        new NC0001Sort().quickSort(pool, 0, pool.length - 1);
        System.out.println(JSON.toJSONString(pool));
        System.out.println("样本：");
        int[] sample = new NC0001ReservoirSampling().sampling(pool, K);
        new NC0001Sort().mergeSort(sample, 0, sample.length - 1);
        System.out.println(JSON.toJSONString(sample));
    }
}
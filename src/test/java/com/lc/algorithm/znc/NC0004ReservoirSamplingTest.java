package com.lc.algorithm.znc;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Random;

public class NC0004ReservoirSamplingTest {
    @Test
    public void sampling() throws Exception {
        final int N = 1000;
        final int K = 100;
        int[] pool = new int[N];
        for (int i = 0; i < N; i++) {
            pool[i] = new Random().nextInt(N);
        }
        System.out.println("蓄水池：");
        new NC0003Sort().quickSort(pool, 0, pool.length - 1);
        System.out.println(JSON.toJSONString(pool));
        System.out.println("样本：");
        int[] sample = new NC0004ReservoirSampling().sampling(pool, K);
        new NC0003Sort().mergeSort(sample, 0, sample.length - 1);
        System.out.println(JSON.toJSONString(sample));
    }
}
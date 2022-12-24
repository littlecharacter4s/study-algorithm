package com.lc.algorithm;

import java.util.Random;

/**
 * 蓄水池抽样
 * 步骤：
 * 1.假设数据序列的规模为n，需要采样的数量的为k；
 * 2.首先构建一个可容纳k个元素的数组，将序列的前k个元素放入数组中；
 * 3.然后从第 i ∈ [k,n) 个元素开始遍历池，随机从 [0,i] 范围抽取一个下标 x，若 x ∈ [0,k)，则将 x 位置的抽样替换为 i 位置的样本；否则什么都不做。
 *   当遍历完所有元素之后，数组中剩下的元素即为所需采取的样本。
 */
public class NC0001ReservoirSampling {
    public int[] sampling(int[] pool, int k) {
        int[] result = new int[k];
        // 前k个元素直接放入数组中
        for (int i = 0; i < k; i++) {
            result[i] = pool[i];
        }
        // k+1个元素开始进行概率采样
        for (int i = k; i < pool.length; i++) {
            int x = new Random().nextInt(i + 1);
            if (x < k) {
                result[x] = pool[i];
            }
        }
        return result;
    }
}

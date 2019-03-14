package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class N0046FullPermutationTest {
    @Test
    public void testPermute() throws Exception {
        N0046FullPermutation permutation = new N0046FullPermutation();
        int[] nums = new int[]{1,2,3};
        System.out.println(JSON.toJSONString(permutation.permute(nums)));
        nums = new int[]{1,2,2};
        System.out.println(JSON.toJSONString(permutation.permute(nums)));
    }
}
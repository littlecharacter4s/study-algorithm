package com.lc.algorithm;

import org.junit.Test;

public class LC0053MaximumSubarrayTest {
    @Test
    public void testMaxSubArray() throws Exception {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int result = new LC0053MaximumSubarray().maxSubArray(nums);
        System.out.println("结果:" + result);
    }
}
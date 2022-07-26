package com.lc.algorithm.structure.array;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author gujixian
 * @since 2022/7/26
 */
public class LC0560SubarraySumEqualsKTest {
    @Test
    public void test() {
        LC0560SubarraySumEqualsK subarraySumEqualsK = new LC0560SubarraySumEqualsK();
        int[] nums = {1,2,3,3};
        subarraySumEqualsK.subarraySum(nums, 6);
    }
}
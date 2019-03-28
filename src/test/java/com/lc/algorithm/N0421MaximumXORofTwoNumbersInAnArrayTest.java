package com.lc.algorithm;

import org.junit.Test;

public class N0421MaximumXORofTwoNumbersInAnArrayTest {
    @Test
    public void testFindMaximumXOR() throws Exception {
        int[] nums = new int[]{3,10,5,25,26,8};
        int result = new N0421MaximumXORofTwoNumbersInAnArray().findMaximumXOR(nums);
        System.out.println("result = " + result);
    }
}
package com.lc.algorithm;

import com.lc.algorithm.LC0421MaximumXORofTwoNumbersInAnArray;
import org.junit.Test;

public class LC0421MaximumXORofTwoNumbersInAnArrayTest {
    @Test
    public void testFindMaximumXOR() throws Exception {
        int[] nums = new int[]{3,10,5,25,26,8};
        int result = new LC0421MaximumXORofTwoNumbersInAnArray().findMaximumXOR(nums);
        System.out.println("result = " + result);
    }
}
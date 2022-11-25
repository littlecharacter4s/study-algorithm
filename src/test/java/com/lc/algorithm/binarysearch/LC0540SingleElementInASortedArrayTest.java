package com.lc.algorithm.binarysearch;

import org.junit.Test;

public class LC0540SingleElementInASortedArrayTest {
    @Test
    public void test() {
        LC0540SingleElementInASortedArray singleElementInASortedArray = new LC0540SingleElementInASortedArray();
        int[] nums = {1,1,2,2,3,4,4,5,5};
        System.out.println(singleElementInASortedArray.singleNonDuplicate(nums));
    }
}
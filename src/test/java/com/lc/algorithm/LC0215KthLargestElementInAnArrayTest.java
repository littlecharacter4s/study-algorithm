package com.lc.algorithm;

import com.lc.algorithm.LC0215KthLargestElementInAnArray;
import org.junit.Test;

public class LC0215KthLargestElementInAnArrayTest {
    @Test
    public void testFindKthLargestElement() throws Exception {
        int[] elements = new int[]{3,2,3,1,2,4,5,5,6};
        LC0215KthLargestElementInAnArray kth = new LC0215KthLargestElementInAnArray();
        int result = kth.findKthLargestElement(elements, 4);
        System.out.println(result);
    }
}
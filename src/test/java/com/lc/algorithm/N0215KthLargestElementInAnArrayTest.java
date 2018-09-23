package com.lc.algorithm;

import org.junit.Test;

public class N0215KthLargestElementInAnArrayTest {
    @Test
    public void testFindKthLargestElement() throws Exception {
        int[] elements = new int[]{3,2,3,1,2,4,5,5,6};
        N0215KthLargestElementInAnArray kth = new N0215KthLargestElementInAnArray();
        int result = kth.findKthLargestElement(elements, 4);
        System.out.println(result);
    }
}
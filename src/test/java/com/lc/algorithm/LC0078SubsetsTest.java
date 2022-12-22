package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import com.lc.algorithm.LC0078Subsets;
import org.junit.Test;

import java.util.List;

public class LC0078SubsetsTest {
    @Test
    public void subsets() throws Exception {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = new LC0078Subsets().subsets(nums);
        System.out.println(JSON.toJSONString(result));
    }

}
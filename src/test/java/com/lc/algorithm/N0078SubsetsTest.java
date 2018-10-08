package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class N0078SubsetsTest {
    @Test
    public void subsets() throws Exception {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = new N0078Subsets().subsets(nums);
        System.out.println(JSON.toJSONString(result));
    }

}
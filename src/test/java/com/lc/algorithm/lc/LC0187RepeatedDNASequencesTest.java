package com.lc.algorithm.lc;

import com.alibaba.fastjson.JSON;
import com.lc.algorithm.lc.LC0187RepeatedDNASequences;
import org.junit.Test;

import java.util.List;

public class LC0187RepeatedDNASequencesTest {
    @Test
    public void findRepeatedDnaSequences() throws Exception {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> result = new LC0187RepeatedDNASequences().findRepeatedDnaSequences(s);
        System.out.println(JSON.toJSONString(result));
    }
}
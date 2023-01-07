package com.lc.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LC0187RepeatedDNASequences {
    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> result = new LC0187RepeatedDNASequences().findRepeatedDnaSequences(s);
        System.out.println("原串：" + s);
        System.out.println("结果：" + JSON.toJSONString(result));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> sequenceMap = new HashMap<>();
        int length = s.length();
        String sequence;
        for (int i = 0; i <= length - 10; i++) {
            sequence = s.substring(i, i + 10);
            if (sequenceMap.containsKey(sequence)) {
                sequenceMap.put(sequence, sequenceMap.get(sequence) + 1);
            } else {
                sequenceMap.put(sequence, 1);
            }
        }
        sequenceMap.forEach((key, value) -> {
            if (value > 1) {
                result.add(key);
            }
        });
        return result;
    }

    public List<String> findRepeatedDnaSequencesSenior(String s) {
        Set<String> sequences = new HashSet<>();
        Set<String> sequencesResult = new HashSet<>();
        int length = s.length();
        for (int i = 0; i <= length - 10; i++) {
            String sequence = s.substring(i, i + 10);
            if (!sequences.add(sequence)) {
                sequencesResult.add(sequence);
            }
        }
        return new ArrayList<>(sequencesResult);
    }

    public List<String> findRepeatedDnaSequencesEfficient(String s) {
        List<String> result = new ArrayList<>();
        // 最多26个（'Z' - 'A'）
        byte[] map = new byte[26];
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        Set<Integer> sequences1 = new HashSet<>();
        Set<Integer> sequences2 = new HashSet<>();
        int length = s.length();
        int sequence;
        for (int i = 0; i < length - 9; i++) {
            sequence = 0;
            for (int j = i; j < i + 10; j++) {
                sequence <<= 2;
                sequence |= map[s.charAt(j) - 'A'];
            }
            if (!sequences1.add(sequence) && sequences2.add(sequence)) {
                result.add(s.substring(i, i + 10));
            }
        }
        return result;
    }
}

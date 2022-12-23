package com.lc.structure.string.kmp;

/**
 * @author gujixian
 * @since 2022/12/23
 */
public class KmpTest {
    public static void main(String[] args) {
        String s1 = "ijklmnabcabxyzsrtabcabdxxx";
        String s2 = "abcabd";
        System.out.println(s1.contains(s2));
    }
}

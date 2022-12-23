package com.lc.structure.string;

import com.alibaba.fastjson.JSON;

/**
 * @author gujixian
 * @since 2022/12/24
 */
public class Manacher {
    public static void main(String[] args) {
        String s = "xabcbastsabcbay";
        System.out.println(JSON.toJSONString(new Manacher().manacher(s)));
    }

    public int[] manacher(String s) {
        if (s == null || s.length() == 0) {
            return new int[]{};
        }
        // abc -> #a#b#c#
        char[] cs = manacherString(s);
        System.out.println(JSON.toJSONString(cs));
        // 每个位置的回文半径
        int[] radius = new int[cs.length];
        // 整体最右回文边界
        int right = -1;
        // 整体最右回文边界更新时对应的回文中心
        int center = -1;


        // todo:gujixian xx


        return radius;
    }

    private char[] manacherString(String s) {
        char[] cs = new char[s.length() * 2 + 1];
        cs[0] = '#';
        int index = 1;
        for (char c : s.toCharArray()) {
            cs[index++] = c;
            cs[index++] = '#';
        }
        return cs;
    }
}

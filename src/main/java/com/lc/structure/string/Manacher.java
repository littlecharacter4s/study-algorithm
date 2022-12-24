package com.lc.structure.string;

import com.alibaba.fastjson.JSON;

/**
 * Manacher 算法
 *
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
        // abc -> a#b#c
        char[] cs = manacherString(s);
        System.out.println(JSON.toJSONString(cs));
        // 每个位置的回文半径
        int[] radius = new int[cs.length];
        // 整体最右回文边界
        int right = -1;
        // 最新的整体最右回文边界对应的回文中心
        int center = -1;
        // 构建回文半径数组
        for (int i = 0; i < cs.length; i++) {
            if (i > right) { // 整体最右回文边界罩不住 i 位置
                int r = getRadius(cs, i, i);
                radius[i] = r;
                // 重新计算整体最右回文边界和对应的回文中心
                int newRight = i + r - 1;
                if (newRight > right) {
                    right = newRight;
                    center = i;
                }
            } else { // 整体最右回文边界罩得住 i 位置
                int cLeft = 2 * center - right;
                int iCenter = 2 * center - i;
                int iLeft = iCenter - radius[iCenter] + 1;
                int iDistance = iCenter - cLeft + 1;
                if (iLeft == cLeft) {
                    int r = iDistance + getRadius(cs, i - iDistance, i + iDistance);
                    radius[i] = r;
                    // 重新计算整体最右回文边界和对应的回文中心
                    int newRight = i + r - 1;
                    if (newRight > right) {
                        right = newRight;
                        center = i;
                    }
                } else {
                    radius[i] = Math.min(iDistance, radius[iCenter]);
                }
            }
        }
        return radius;
    }

    private int getRadius(char[] cs, int left, int right) {
        int count = 0;
        while (left >= 0 && right < cs.length && cs[left] == cs[right]) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    private char[] manacherString(String s) {
        char[] cs = new char[s.length() * 2 - 1];
        int index = 0;
        for (char c : s.toCharArray()) {
            cs[index++] = c;
            if (index < cs.length) {
                cs[index++] = '#';
            }
        }
        return cs;
    }
}

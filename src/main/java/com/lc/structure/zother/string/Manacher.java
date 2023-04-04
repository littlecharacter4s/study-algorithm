package com.lc.structure.zother.string;

import com.alibaba.fastjson.JSON;

/**
 * Manacher 算法
 *
 * @author gujixian
 * @since 2022/12/24
 */
public class Manacher {
    public static void main(String[] args) {
        String s = "cbbd";
        char[] cs = new Manacher().manacherString(s);
        System.out.print("[");
        for (int i = 0; i < cs.length; i++) {
            if (i == cs.length - 1) {
                System.out.println(cs[i] + "]");
            } else {
                System.out.print(cs[i] + ",");
            }
        }
        System.out.println(JSON.toJSONString(new Manacher().manacher(s)));
    }

    public int[] manacher(String s) {
        if (s == null || s.length() == 0) {
            return new int[]{};
        }
        // abc -> #a#b#c#
        char[] cs = manacherString(s);
        // 每个位置的回文半径
        int[] radius = new int[cs.length];
        // 整体最右回文边界
        int right = -1;
        // 最新的整体最右回文边界对应的回文中心
        int center = -1;
        // 构建回文半径数组
        for (int i = 0; i < cs.length; i++) {
            // i 位置的回文半径
            int r = 0;
            if (i > right) { // 整体最右回文边界罩不住 i 位置
                r = getRadius(cs, i, i);
            } else { // 整体最右回文边界罩得住 i 位置
                /*
                 * x x   a   b   c        d         e    d c b    a   t    s   t a b c d e d   c   x x
                 *     iLeft   cLeft--iDistance--iCenter       iRight   center           i   right
                 */
                int cLeft = 2 * center - right;
                int iCenter = 2 * center - i;
                int iLeft = iCenter - radius[iCenter] + 1;
                int iDistance = iCenter - cLeft + 1;
                if (iLeft == cLeft) {
                    r = iDistance + getRadius(cs, i - iDistance, i + iDistance);
                } else {
                    r = Math.min(iDistance, radius[iCenter]);
                }
            }
            radius[i] = r;
            // 重新计算整体最右回文边界和对应的回文中心
            int newRight = i + r - 1;
            if (newRight > right) {
                right = newRight;
                center = i;
            }
        }
        return radius;
    }

    public char[] manacherString(String s) {
        char[] cs = new char[s.length() * 2 + 1];
        cs[0] = '#';
        int index = 1;
        for (char c : s.toCharArray()) {
            cs[index++] = c;
            cs[index++] = '#';
        }
        return cs;
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
}

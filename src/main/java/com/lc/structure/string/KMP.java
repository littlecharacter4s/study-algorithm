package com.lc.structure.string;

/**
 * @author gujixian
 * @since 2022/12/23
 */
public class KMP {
    public static void main(String[] args) {
        String s1 = "ijklmnabcabxyzsrtabcabdxxx";
        String s2 = "abcabd";
        System.out.println(s1.indexOf(s2));
        System.out.println(new KMP().indexOf(s1, s2));
        System.out.println("-----JDK VS KMP-----");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            s1.indexOf(s2);
        }
        long end = System.currentTimeMillis();
        System.out.println("JDK：" + (end - start) + "ms");
        KMP kmp = new KMP();
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            kmp.indexOf(s1, s2);
        }
        end = System.currentTimeMillis();
        System.out.println("KMP：" + (end - start) + "ms");
    }

    public int indexOf(String s1, String s2) {
        // 过滤入参
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        // 预处理
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[] help = getHelp(c2);
        int i = 0;
        int j = 0;
        // 匹配
        while (i < c1.length && j < c2.length) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else if (help[j] == -1) {
                i++;
            } else {
                j = help[j];
            }
        }
        return j == c2.length ? (i - j) : -1;
    }

    /**
     * 正常版：好理解
     */
    private int[] getHelp(char[] c) {
        if (c.length == 1) {
            return new int[]{-1};
        }
        int[] help = new int[c.length];
        help[0] = -1;
        help[1] = 0;
        int i = 2;
        while (i < help.length) {
            int j = help[i - 1];
            while (j != -1) {
                if (c[i - 1] == c[j]) {
                    help[i++] = ++j;
                    break;
                } else {
                    j = help[j];
                }
            }
            if (j == -1) {
                help[i++] = 0;
            }
        }
        return help;
    }

    /**
     * 下标换算版：难理解（可以忽略）
     */
    private int[] getHelpX(char[] c) {
        if (c.length == 1) {
            return new int[]{-1};
        }
        int[] help = new int[c.length];
        help[0] = -1;
        help[1] = 0;
        int i = 2;
        int j = 0;
        while (i < help.length) {
            if (c[i - 1] == c[j]) {
                help[i++] = ++j;
            } else if (j > 0) {
                j = help[j];
            } else {
                help[i++] = 0;
            }
        }
        return help;
    }
}

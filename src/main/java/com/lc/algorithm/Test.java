package com.lc.algorithm;

public class Test {
    public static void main(String[] args) {
        int[][] a = {{1,2,3},{4,5,6,},{7,8,9}};
        int[][] sum = new int[a.length][a[0].length];
        sum[0][0] = a[0][0];
        for (int i = 1; i < a.length; i++) {
            sum[i][0] = sum[i-1][0] + a[i][0];
        }
        for (int j = 1; j < a[0].length; j++) {
            sum[0][j] = sum[0][j-1] + a[0][j];
        }

        for (int i = 1; i < a.length; i++) {
            int rowSum = a[i][0];
            for (int j = 1; j < a.length; j++) {
                rowSum += a[i][j];
                sum[i][j] = sum[i-1][j] + rowSum;
            }
        }


        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < sum[0].length; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
    }
}

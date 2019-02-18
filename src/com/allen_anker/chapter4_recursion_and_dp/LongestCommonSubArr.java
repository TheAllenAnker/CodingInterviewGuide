package com.allen_anker.chapter4_recursion_and_dp;

import java.util.Arrays;

public class LongestCommonSubArr {
    /**
     * An optimized solution, with space complexity O(1).
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String longestCommonSubArr2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return "";
        }

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int max = 0;
        int end = 0;
        int row = 0;
        int col = chars2.length - 1;
        while (row < chars1.length) {
            int i = row;
            int j = col;
            int len = 0;
            while (i < chars1.length && j < chars2.length) {
                if (chars1[i] != chars2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                if (len > max) {
                    max = len;
                    end = i;
                }
                i++;
                j++;
            }
            if (col == 0) {
                row++;
            } else {
                col--;
            }
        }

        return str1.substring(end - max + 1, end + 1);
    }

    /**
     * Basic solution, with time complexity O(MxN), space complexity O(MxN).
     *
     * @param str1 length M
     * @param str2 length N
     * @return
     */
    public static String longestCommonSubArr(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return null;
        }
        if (str1.length() == 0 || str2.length() == 0) {
            return "";
        }

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] dp = getdp(chars1, chars2);

        int max = 0;
        int end = 0;
        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars2.length; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    end = i;
                }
            }
        }
        char[] res = Arrays.copyOfRange(chars1, end - max + 1, end + 1);

        return String.valueOf(res);
    }

    private static int[][] getdp(char[] chars1, char[] chars2) {
        int row = chars1.length, col = chars2.length;
        int[][] dp = new int[chars1.length][chars2.length];
        char c1 = chars1[0], c2 = chars2[0];
        for (int i = 0; i < row; i++) {
            if (chars1[i] == c2) {
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < col; i++) {
            if (chars2[i] == c1) {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubArr("1AB2345CD", "12345EF"));
        System.out.println(longestCommonSubArr2("1AB2345CD", "12345EF"));
    }
}

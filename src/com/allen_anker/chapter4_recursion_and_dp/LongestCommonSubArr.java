package com.allen_anker.chapter4_recursion_and_dp;

public class LongestCommonSubArr {
    public static String longestCommonSubArr(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return null;
        }
        if (str1.length() == 0 || str2.length() == 0) {
            return "";
        }

        int row = str1.length(), col = str2.length();
        int[][] dp = new int[row][col];
        int c1 = str1.charAt(0), c2 = str2.charAt(0);
        for (int i = 0; i < row; i++) {
            if (str1.charAt(i) == c2) {
                for (int j = i; j < row; j++) {
                    dp[j][0] = 1;
                }
                break;
            }
        }
        for (int i = 0; i < col; i++) {
            if (str2.charAt(i) == c1) {
                for (int j = i; j < col; j++) {
                    dp[0][j] = 1;
                }
                break;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        return generateStrWithDp(str1, str2, dp);
    }

    private static String generateStrWithDp(String str1, String str2, int[][] dp) {
        int row = dp.length, col = dp[0].length;
        char[] res = new char[dp[row - 1][col - 1]];

        int index = res.length - 1;
        int i = row - 1, j = col - 1;
        while (index >= 0) {
            int curr = dp[i][j];
            if (i > 0 && curr == dp[i - 1][j]) {
                i--;
            } else if (j > 0 && curr == dp[i][j - 1]) {
                j--;
            } else {
                res[index--] = str1.charAt(i);
                i--;
                j--;
            }
        }

        return new String(res);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubArr("1A2C3D4B56", "B1D23CA45B6A"));
    }
}

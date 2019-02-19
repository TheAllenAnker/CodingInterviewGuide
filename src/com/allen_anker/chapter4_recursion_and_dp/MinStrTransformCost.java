package com.allen_anker.chapter4_recursion_and_dp;

public class MinStrTransformCost {
    /**
     * With time complexity O(MxN), space complexity O(MxN).
     *
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */
    public static int minCost(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Invalid parameter(s): str cannot be null");
        }

        int rows = str1.length(), cols = str2.length();
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 1; i <= cols; i++) {
            dp[0][i] = ic * i;
        }
        for (int i = 1; i <= rows; i++) {
            dp[i][0] = dc * i;
        }
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dc + dp[i - 1][j]);
                dp[i][j] = Math.min(dp[i][j], ic + dp[i][j - 1]);
            }
        }

        return dp[rows][cols];
    }

    public static void main(String[] args) {
        System.out.println(minCost("ab12cd3", "abcdf", 5, 3, 2));
    }
}

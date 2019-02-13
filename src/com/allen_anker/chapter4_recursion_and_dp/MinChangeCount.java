package com.allen_anker.chapter4_recursion_and_dp;

public class MinChangeCount {
    public static int minChangeCount1(int[] values, int aim) {
        if (values == null || values.length == 0 || aim < 0) {
            throw new IllegalArgumentException("Invalid parameter(s)");
        }
        if (aim == 0) {
            return 0;
        }

        int length = values.length;
        int[] dp = new int[aim + 1];
        for (int i = 1; i < aim + 1; i++) {
            dp[i] = i % values[0] == 0 ? i / values[0] : Integer.MAX_VALUE;
        }
        for (int i = 1; i < length; i++) {
            for (int j = 1; j < aim + 1; j++) {
                for (int k = j - values[i], count = 1; k >= 0 && dp[k] != Integer.MAX_VALUE; k -= values[i], count++) {
                    dp[j] = Math.min(dp[j], dp[k] + count);
                }
            }
        }

        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }

    public static void main(String[] args) {
        int[] values = {5, 3};
        System.out.println(minChangeCount1(values, 2));
    }
}

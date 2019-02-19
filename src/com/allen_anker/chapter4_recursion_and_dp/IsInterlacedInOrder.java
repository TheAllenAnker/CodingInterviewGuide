package com.allen_anker.chapter4_recursion_and_dp;

public class IsInterlacedInOrder {
    /**
     * Both time and space complexity is O(MxN).
     *
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public static boolean isInterlacedInOrder(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) {
            throw new IllegalArgumentException("Invalid parameter");
        }
        if (aim.length() != str1.length() + str2.length()) {
            return false;
        }

        int rows = str1.length(), cols = str2.length();
        boolean[][] dp = new boolean[rows + 1][cols + 1];
        dp[0][0] = true;
        for (int i = 1; i <= rows; i++) {
            if (str1.charAt(i - 1) != aim.charAt(i - 1)) {
                break;
            }
            dp[i][0] = true;
        }
        for (int i = 1; i <= cols; i++) {
            if (str2.charAt(i - 1) != aim.charAt(i - 1)) {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                dp[i][j] = dp[i - 1][j] && str1.charAt(i - 1) == aim.charAt(i + j - 1)
                        || dp[i][j - 1] && str2.charAt(j - 1) == aim.charAt(i + j - 1);
            }
        }

        return dp[rows][cols];
    }

    public static void main(String[] args) {
        System.out.println(isInterlacedInOrder("AB", "12", "AB12"));
        System.out.println(isInterlacedInOrder("AB", "12", "AB21"));
        System.out.println(isInterlacedInOrder("AB", "12", "A1B2"));
        System.out.println(isInterlacedInOrder("AB", "12", "A12B"));
        System.out.println(isInterlacedInOrder("AB", "12", "1A2B"));
    }
}

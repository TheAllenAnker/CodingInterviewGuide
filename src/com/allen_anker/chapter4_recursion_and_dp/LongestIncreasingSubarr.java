package com.allen_anker.chapter4_recursion_and_dp;

import java.util.Arrays;

public class LongestIncreasingSubarr {
    /**
     * Time complexity: O(nlogn)
     *
     * @param arr
     * @return
     */
    public static int[] getLongestIncreasingArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        int[] dp = getDPArr(arr);
        return generateLIAWithDPArr(arr, dp);
    }

    private static int[] getDPArr(int[] arr) {
        int length = arr.length;
        int[] dp = new int[length];
        int[] ends = new int[length];
        int right = 0;
        dp[0] = 1;
        ends[0] = arr[0];
        for (int i = 1; i < length; i++) {
            int l = 0, r = right;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (ends[mid] < arr[i]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            right = right > l ? right : l;
            ends[l] = arr[i];
            dp[i] = l + 1;
        }

        return dp;
    }

    private static int[] generateLIAWithDPArr(int[] arr, int[] dp) {
        int maxI = 0;
        int max = dp[0];
        for (int i = 1; i < arr.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
                maxI = i;
            }
        }
        int[] res = new int[max];
        res[max - 1] = arr[maxI];
        int lastI = maxI;
        for (int i = max - 2; i >= 0; i--) {
            for (int j = lastI - 1; j >= 0; j--) {
                if (arr[j] < res[i + 1] && dp[j] == dp[lastI] - 1) {
                    res[i] = arr[j];
                    lastI = j;
                    break;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        System.out.println(Arrays.toString(getLongestIncreasingArr(arr)));
    }
}

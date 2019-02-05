package com.allen_anker.chapter3_binarytree;

public class PossibleBT {
    /**
     * If a binary tree's in-order traversal array is [1, 2, 3,...,n].
     * What is the number of ways to build this binary tree.
     *
     * @param n
     * @return
     */
    public static int numberOfPossibleBT(int n) {
        if (n <= 1) {
            return 1;
        }

        int[] num = new int[n + 1];
        num[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                num[i] += num[j - 1] * num[i - j];
            }
        }

        return num[n];
    }

    public static void main(String[] args) {
        System.out.println(numberOfPossibleBT(2));
    }
}

package com.allen_anker.chapter4_recursion_and_dp;

public class Fabonacci {
    public static int fi2(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Invalid parameter");
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int a = 1, b = 1;
        int result = a + b;
        for (int i = 3; i < n; i++) {
            a = b;
            b = result;
            result = a + b;
        }

        return result;
    }

    public static int fi(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Invalid parameter");
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        return fi(n - 1) + fi(n - 2);
    }
    public static void main(String[] args) {
        for (int i = 1; i < 7; i++) {
            System.out.print(fi(i) + " ");
        }
        System.out.println();
        for (int i = 1; i < 7; i++) {
            System.out.print(fi2(i) + " ");
        }
    }
}

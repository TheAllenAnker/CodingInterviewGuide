package com.allen_anker.chapter4_recursion_and_dp;

public class Hanoi {
    public static void pringHanoiSolution(int n) {
        if (n > 0) {
            hanoiCore(n, "left", "mid", "right");
        }
    }

    private static void hanoiCore(int n, String from, String mid, String to) {
        if (n == 1) {
            System.out.println("Moves from " + from + " to " + to);
        } else {
            hanoiCore(n - 1, from, to, mid);
            hanoiCore(1, from, mid, to);
            hanoiCore(n - 1, mid, from, to);
        }
    }

    public static void main(String[] args) {
        pringHanoiSolution(4);
    }
}

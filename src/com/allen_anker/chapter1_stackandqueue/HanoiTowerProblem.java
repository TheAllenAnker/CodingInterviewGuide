package com.allen_anker.chapter1_stackandqueue;

public class HanoiTowerProblem {
    public void printHanoiSolution(int n) {
        hanoiCore("left", "mid", "right", n);
    }

    private void hanoiCore(String from, String mediator, String to, int n) {
        if (n == 1) {
            System.out.println("Moves from " + from + " to " + to);
        } else {
            hanoiCore(from, to, mediator, n - 1);
            hanoiCore(from, mediator, to, 1);
            hanoiCore(mediator, from, to, n - 1);
        }
    }

    public static void main(String[] args) {
        new HanoiTowerProblem().printHanoiSolution(3);
    }
}

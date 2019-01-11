package com.allen_anker.chapter1_stackandqueue;

import java.util.LinkedList;

public class MaxAndMinInSubArr {
    /**
     * Find the number of sub-arrays in an array that satisfy max(in arr) - min(in arr) <= num.
     *
     * @param arr the given arr
     * @param num
     * @return
     */
    public int numberOfSubarr(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int res = 0;
        int start = 0, end = 0;
        LinkedList<Integer> maxQueue = new LinkedList<>();
        LinkedList<Integer> minQueue = new LinkedList<>();
        while (end < arr.length) {
            while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[end]) {
                maxQueue.pollLast();
            }
            maxQueue.offer(end);
            while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[end]) {
                minQueue.pollLast();
            }
            minQueue.offer(end);
            if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
                res += end - start;
                start++;
                if (maxQueue.peekFirst() < start) {
                    maxQueue.pollFirst();
                }
                if (minQueue.peekFirst() < start) {
                    minQueue.peekFirst();
                }
            }
            end++;
        }
        res += end - start;

        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(new MaxAndMinInSubArr().numberOfSubarr(arr, 4));
    }
}

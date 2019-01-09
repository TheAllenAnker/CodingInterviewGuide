package com.allen_anker.chapter1_stackandqueue;

import java.util.Arrays;
import java.util.LinkedList;

public class MaxInWindow {
    public int[] getMaxesInWindow(int[] arr, int windowSize) {
        if (arr == null || arr.length == 0 || windowSize > arr.length) {
            throw new IllegalArgumentException("Invalid parameter(s)");
        }

        int length = arr.length;
        int[] res = new int[length - windowSize + 1];
        LinkedList<Integer> maxIndexes = new LinkedList<>();
        int index = 0;
        for (int i = 0; i <= length; i++) {
            if (i >= windowSize) {
                int currMaxIndex = maxIndexes.peekFirst();
                res[index++] = arr[currMaxIndex];
                if (i == length) {
                    break;
                }
                if (i - windowSize >= currMaxIndex) {
                    maxIndexes.pollFirst();
                }
            }
            while (!maxIndexes.isEmpty() && arr[maxIndexes.peekLast()] <= arr[i]) {
                maxIndexes.pollLast();
            }
            maxIndexes.offer(i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        System.out.println(Arrays.toString(new MaxInWindow().getMaxesInWindow(arr, 3)));
    }
}

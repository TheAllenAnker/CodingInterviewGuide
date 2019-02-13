package com.allen_anker.chapter4_recursion_and_dp;

import java.util.HashMap;

public class WaysOfGettingChange {
    public static int waysOfGettingChange2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            throw new IllegalArgumentException("Invalid parameter(s)");
        }
        if (aim == 0) {
            return 1;
        }

        HashMap<String, Integer> resMap = new HashMap<>();
        return process2(arr, 0, aim, resMap);
    }

    public static int waysOfGettingChange1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            throw new IllegalArgumentException("Invalid parameter(s)");
        }
        if (aim == 0) {
            return 1;
        }

        return process(arr, 0, aim);
    }

    private static int process2(int[] arr, int index, int aim, HashMap<String, Integer> resMap) {
        if (resMap.containsKey(index + "_" + aim)) {
            return resMap.get(index + "_" + aim);
        }

        int res = 0;
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        for (int i = 0; arr[index] * i <= aim; i++) {
            res += process(arr, index + 1, aim - i * arr[index]);
        }
        resMap.put(index + "_" + aim, res);

        return res;
    }

    private static int process(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        for (int i = 0; arr[index] * i <= aim; i++) {
            res += process(arr, index + 1, aim - i * arr[index]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 25, 1};
        System.out.println(waysOfGettingChange1(arr, 15));
        System.out.println(waysOfGettingChange2(arr, 15));
    }
}

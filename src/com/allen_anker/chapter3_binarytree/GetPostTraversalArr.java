package com.allen_anker.chapter3_binarytree;

import java.util.Arrays;
import java.util.HashMap;

public class GetPostTraversalArr {
    /**
     * Return the binary tree's post-order traversal array, using its pre-order and in-order traversal arrays.
     * Rebuilding the tree with the two arrays is not allowed.
     *
     * @param pre
     * @param in
     * @return
     */
    public static int[] getPostOrderTraversalArr(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            throw new IllegalArgumentException("Invalid parameter(s)");
        }

        int length = pre.length;
        int[] res = new int[length];
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            indexMap.put(in[i], i);
        }
        setPos(pre, 0, length - 1, in, 0, length - 1, res, length - 1, indexMap);

        return res;
    }

    private static int setPos(int[] pre, int preStart, int preEnd,
                              int[] in, int inStart, int inEnd,
                              int[] res, int resI, HashMap<Integer, Integer> indexMap) {
        if (preStart > preEnd) {
            return resI;
        }
        int val = pre[preStart];
        res[resI--] = val;
        int index = indexMap.get(val);
        resI = setPos(pre, preStart + index - inStart + 1, preEnd,
                in, index + 1, inEnd, res, resI, indexMap);
        return setPos(pre, preStart + 1, preStart + index - inStart,
                in, inStart, index - 1, res, resI, indexMap);
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 8, 9, 3, 6, 7};
        int[] in = {4, 2, 8, 5, 9, 1, 6, 3, 7};
        System.out.println(Arrays.toString(getPostOrderTraversalArr(pre, in)));
    }
}

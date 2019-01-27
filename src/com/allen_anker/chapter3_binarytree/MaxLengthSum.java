package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class MaxLengthSum {
    public static int getMaxLengthPath(TreeNode root, int sum) {
        if (root == null) {
            throw new IllegalArgumentException("Invalid parameter");
        }
        // key is the sum, value is the smallest the value appears
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0);

        return preOrder(root, sum, 0, 1, 0, sumMap);
    }

    private static int preOrder(TreeNode root, int sum, int preSum, int level, int maxLength,
                                HashMap<Integer, Integer> sumMap) {
        if (root == null) {
            return maxLength;
        }

        int currSum = preSum + root.value;
        if (!sumMap.containsKey(currSum)) {
            sumMap.put(currSum, level);
        }
        if (sumMap.containsKey(currSum - sum)) {
            maxLength = Math.max(level - sumMap.get(currSum - sum), maxLength);
        }
        maxLength = preOrder(root.left, sum, currSum, level + 1, maxLength, sumMap);
        maxLength = preOrder(root.right, sum, currSum, level + 1, maxLength, sumMap);
        if (sumMap.get(currSum) == level) {
            sumMap.remove(currSum);
        }

        return maxLength;
    }

    public static void main(String[] args) {

    }
}

package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class BuildBalancedBSTWithArr {
    public static TreeNode buildBBSTWithSortedArr(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        return buildTreeCore(values, 0, values.length - 1);
    }

    private static TreeNode buildTreeCore(int[] values, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode head = new TreeNode(values[mid]);
        head.left = buildTreeCore(values, start, mid - 1);
        head.right = buildTreeCore(values, mid + 1, end);

        return head;
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5, 6, 7};
        TreeUtils.printTreeBeautifully(buildBBSTWithSortedArr(values));
    }
}

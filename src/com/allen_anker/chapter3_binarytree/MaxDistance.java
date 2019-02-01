package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class MaxDistance {
    /**
     * Time complexity: O(N), N is the number of nodes in binary tree.
     *
     * @param head
     * @return
     */
    public static int getMaxDistance(TreeNode head) {
        if (head == null) {
            return 0;
        }

        int[] record = new int[1];
        return postOrder(head, record);
    }

    private static int postOrder(TreeNode head, int[] record) {
        if (head == null) {
            record[0] = 0;
            return 0;
        }

        int lMax = postOrder(head.left, record);
        int maxFromLeft = record[0];
        int rMax = postOrder(head.right, record);
        int maxFromRight = record[0];
        record[0] = Math.max(maxFromLeft, maxFromRight) + 1;
        int currMax = maxFromLeft + maxFromRight + 1;

        return Math.max(Math.max(lMax, rMax), currMax);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
        TreeUtils.printTreeBeautifully(root);
        System.out.println(getMaxDistance(root));
    }
}

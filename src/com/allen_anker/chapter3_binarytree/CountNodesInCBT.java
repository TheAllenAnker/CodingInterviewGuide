package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class CountNodesInCBT {
    /**
     * Get the number of nodes in a complete binary tree.
     * With time complexity of O(h^2), h is the height of the given binary tree.
     *
     * @param head
     * @return
     */
    public static int getNumberOfNodesInCBT(TreeNode head) {
        if (head == null) {
            return 0;
        }

        return getNumberOfNodesCore(head, 1, getLeftHeight(head));
    }

    private static int getNumberOfNodesCore(TreeNode head, int level, int height) {
        if (head == null) {
            return 0;
        }

        int rightLeftMostHeight = getLeftHeight(head.right);
        if (rightLeftMostHeight + level == height) {
            return getNumberOfNodesCore(head.right, level + 1, height) + (int) Math.pow(2, height - level);
        } else {
            return getNumberOfNodesCore(head.left, level + 1, height) + (int) Math.pow(2, height - level - 1);
        }
    }

    private static int getLeftHeight(TreeNode head) {
        if (head == null) {
            return 0;
        }

        int height = 1;
        while (head.left != null) {
            height++;
            head = head.left;
        }

        return height;
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtils.getSimpleTree();
        head.right.right = null;
        head.right.left = null;
        head.left.right = null;
        TreeUtils.printTreeBeautifully(head);
        System.out.println(getNumberOfNodesInCBT(head));
    }
}

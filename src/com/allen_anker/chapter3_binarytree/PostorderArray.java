package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class PostorderArray {
    /**
     * In post-order traversal, the last element in tree must be the root of the current sub-tree.
     *
     * @param values
     * @return
     */
    public static boolean isPostorderArrayOfABST(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }

        return isPostCore(values, 0, values.length - 1);
    }

    public static boolean isPostCore(int[] value, int start, int end) {
        if (start == end) {
            return true;
        }

        int smaller = -1;
        int bigger = end;
        for (int i = start; i < end; i++) {
            if (value[i] < value[end]) {
                smaller = i;
            } else {
                bigger = bigger == end ? i : bigger;
            }
        }
        if (smaller == -1 || bigger == end) {
            return isPostCore(value, start, end - 1);
        }
        // the two borders should not meet
        if (smaller != bigger - 1) {
            return false;
        }

        return isPostCore(value, start, smaller) && isPostCore(value, bigger, end - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        TreeUtils.printTreeBeautifully(root);
        int[] value = new int[]{1, 3, 2, 5, 7, 6, 4};
        BinaryTreeTraversal.printPostorder(root);
        System.out.println(isPostorderArrayOfABST(value));
        System.out.println(isPostorderArrayOfABST(new int[]{1, 5, 3, 4}));
    }
}

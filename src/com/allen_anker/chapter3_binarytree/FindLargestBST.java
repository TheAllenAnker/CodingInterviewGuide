package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class FindLargestBST {
    public static TreeNode findLargestBSTInBT(TreeNode root) {
        if (root == null) {
            return null;
        }

        return postOrder(root, new int[3]);
    }

    private static TreeNode postOrder(TreeNode root, int[] records) {
        if (root == null) {
            records[0] = 0;
            records[1] = Integer.MAX_VALUE;
            records[2] = Integer.MIN_VALUE;
            return null;
        }

        int value = root.value;
        TreeNode left = root.left;
        TreeNode lBST = postOrder(left, records);
        int lSize = records[0];
        int lMin = records[1];
        int lMax = records[2];
        TreeNode right = root.right;
        TreeNode rBST = postOrder(right, records);
        int rSize = records[0];
        int rMin = records[1];
        int rMax = records[2];
        records[1] = Math.min(lMin, value);
        records[2] = Math.max(rMax, value);
        // left sub-tree and right sub-tree are BST and...
        if (lBST == left && rBST == right && lMax < value && rMin > value) {
            records[0] = lSize + rSize + 1;
            return root;
        }
        records[0] = lSize > rSize ? lSize : rSize;

        return lSize > rSize ? lBST : rBST;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(12);
        root.right.left = new TreeNode(10);
        root.right.left.left = new TreeNode(4);
        root.right.left.right = new TreeNode(14);
        root.right.left.left.left = new TreeNode(2);
        root.right.left.left.right = new TreeNode(5);
        root.right.left.right.left = new TreeNode(11);
        root.right.left.right.right = new TreeNode(15);
        root.right.right = new TreeNode(13);
        root.right.right.left = new TreeNode(20);
        root.right.right.right = new TreeNode(16);
        TreeUtils.printTreeBeautifully(root);
        System.out.println(findLargestBSTInBT(root).value);
    }
}

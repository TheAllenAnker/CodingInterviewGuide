package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class BalancedTree {
    public static boolean isBalancedTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        // build a array object to pass by reference
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(root, 0, res);

        return res[0];
    }

    private static int getHeight(TreeNode root, int height, boolean[] res) {
        if (root == null) {
            return height;
        }
        int lH = getHeight(root.left, height + 1, res);
        if (!res[0]) {
            return height;
        }
        int rH = getHeight(root.right, height + 1, res);
        if (!res[0]) {
            return height;
        }
        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }

        return lH > rH ? lH : rH;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
//        root.left.left.left = new TreeNode(8);
//        root.left.left.left.left = new TreeNode(9);
        TreeUtils.printTreeBeautifully(root);
        System.out.println(isBalancedTree(root));
    }
}

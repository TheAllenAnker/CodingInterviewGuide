package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class IsBSTAndCBT {
    /**
     * Determine if a given binary tree is a complete binary tree.
     *
     * @param root
     * @return
     */
    public static boolean isCBT(TreeNode root) {
        if (root == null) {
            return true;
        }

        return false;
    }

    /**
     * Determine if a given binary tree is a binary search tree.
     *
     * @param root
     * @return
     */
    public static boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        TreeNode curr = root;
        TreeNode temp;
        int preValue = Integer.MIN_VALUE;
        while (curr != null) {
            temp = curr.left;
            if (temp != null) {
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }
                if (temp.right == null) {
                    temp.right = curr;
                    curr = curr.left;
                    continue;
                } else {
                    temp.right = null;
                }
            }
            if (curr.value < preValue) {
                return false;
            }
            preValue = curr.value;
            curr = curr.right;
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode root2 = TreeUtils.getSimpleTree();
        System.out.println(isBST(root2));
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        TreeUtils.printTreeBeautifully(root);
        System.out.println(isBST(root));
    }
}

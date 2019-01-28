package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class TreeInTree {
    public static boolean isTree2InTree1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }

        return isTreeInTreeCore(root1, root2) || isTree2InTree1(root1.left, root2) || isTree2InTree1(root1.right, root2);
    }

    private static boolean isTreeInTreeCore(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || root1.value != root2.value) {
            return false;
        }

        return isTreeInTreeCore(root1.left, root2.left) && isTreeInTreeCore(root1.right, root2.right);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        TreeUtils.printTreeBeautifully(root);
        TreeUtils.printTreeBeautifully(root2);
        System.out.println(isTree2InTree1(root, root2));
    }
}

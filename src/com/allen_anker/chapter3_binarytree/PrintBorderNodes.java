package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PrintBorderNodes {
    public static void printBorderNodes2(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode curr = root;
        ArrayList<TreeNode> leftMostNodes = new ArrayList<>();
        while (curr != null) {
            leftMostNodes.add(curr);
            System.out.print(curr.value + " ");
            curr = curr.left;
        }
        ArrayList<TreeNode> rightMostNodes = new ArrayList<>();
        curr = root.right;
        while (curr != null) {
            rightMostNodes.add(curr);
            curr = curr.right;
        }
        printNotBorderLeafNode(root, leftMostNodes, rightMostNodes);
        for (int i = rightMostNodes.size() - 1; i >= 0; i--) {
            System.out.print(rightMostNodes.get(i).value + " ");
        }
        System.out.println();
    }

    /**
     * Print tree's border nodes with rule 1.
     *
     * @param root
     */
    public static void printBorderNodes1(TreeNode root) {
        if (root == null) {
            return;
        }
        // get the left and right border nodes
        ArrayList<TreeNode> leftNodes = new ArrayList<>();
        ArrayList<TreeNode> rightNodes = new ArrayList<>();
        setEdgeNodes(root, leftNodes, rightNodes);

        for (int i = 0; i < leftNodes.size(); i++) {
            System.out.print(leftNodes.get(i).value + " ");
        }
        printNotBorderLeafNode(root, leftNodes, rightNodes);
        for (int i = rightNodes.size() - 1; i >= 0; i--) {
            TreeNode curr = rightNodes.get(i);
            if (!leftNodes.contains(curr)) {
                System.out.print(curr.value + " ");
            }
        }
        System.out.println();
    }

    private static void setEdgeNodes(TreeNode root, List<TreeNode> leftNodes, List<TreeNode> rightNodes) {
        TreeNode curr = root;
        while (curr != null) {
            leftNodes.add(curr);
            if (curr.left != null) {
                curr = curr.left;
                continue;
            }
            curr = curr.right;
        }
        curr = root;
        while (curr != null) {
            rightNodes.add(curr);
            if (curr.right != null) {
                curr = curr.right;
                continue;
            }
            curr = curr.left;
        }
    }

    private static void printNotBorderLeafNode(TreeNode root, List<TreeNode> leftNodes, List<TreeNode> rightNodes) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && !leftNodes.contains(root) && !rightNodes.contains(root)) {
            System.out.print(root.value + " ");
        }

        printNotBorderLeafNode(root.left, leftNodes, rightNodes);
        printNotBorderLeafNode(root.right, leftNodes, rightNodes);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
        printBorderNodes1(root);
        printBorderNodes2(root);
    }
}

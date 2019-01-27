package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.HashMap;

public class LargestBSTTopoSize {
    public static int getLargestBSTTopoSize(TreeNode root) {
        if (root == null) {
            return 0;
        }

        HashMap<TreeNode, int[]> map = new HashMap<>();

        return postOrder(root, map);
    }

    private static int postOrder(TreeNode root, HashMap<TreeNode, int[]> map) {
        if (root == null) {
            return 0;
        }

        int ls = postOrder(root.left, map);
        int rs = postOrder(root.right, map);
        int value = root.value;
        modifyMap(root.left, value, map, true);
        modifyMap(root.right, value, map, false);
        int[] recordsL = map.get(root.left);
        int[] recordsR = map.get(root.right);
        int lbstSize = recordsL == null ? 0 : recordsL[0] + recordsL[1] + 1;
        int rbstSize = recordsR == null ? 0 : recordsR[0] + recordsR[1] + 1;
        map.put(root, new int[]{lbstSize, rbstSize});

        return Math.max(lbstSize + rbstSize + 1, Math.max(ls, rs));
    }

    private static int modifyMap(TreeNode root, int value, HashMap<TreeNode, int[]> map, boolean s) {
        if (root == null || !map.containsKey(root)) {
            return 0;
        }

        int[] records = map.get(root);
        if ((s && root.value > value) || (!s && root.value < value)) {
            map.remove(root);
            return records[0] + records[1] + 1;
        } else {
            int minus = modifyMap(s ? root.right : root.left, value, map, s);
            if (s) {
                records[1] -= minus;
            } else {
                records[0] -= minus;
            }
            map.put(root, records);
            return minus;
        }
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
        System.out.println(getLargestBSTTopoSize(root));
    }
}
